package homework_project.services;

import homework_project.assemblers.CustomerModelAssembler;
import homework_project.data_setters.DataSetters;
import homework_project.exceptions.CustomerDoesNotExistException;
import homework_project.models.Customer;
import homework_project.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerModelAssembler customerModelAssembler;

    public List<EntityModel<Customer>> allCustomers(){
       return customerRepository.findAll().stream()
                .map(customerModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    public Customer customerFoundById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDoesNotExistException(id));
    }

    public EntityModel<Customer> createCustomer(Customer newCustomer){
        return customerModelAssembler.toModel(customerRepository.save(newCustomer));
    }

    public EntityModel<Customer> updateCustomer(Customer customerWithChanges, Long id) {
        if (!customerRepository.findById(id).isPresent()) {
            throw new CustomerDoesNotExistException(id);
        } else {
            Customer updatedCustomer = customerRepository.findById(id)
                    .map(customer -> {
                        DataSetters.setDataInCustomer(customer, customerWithChanges);
                        return customerRepository.save(customer);
                    }).orElseGet(() -> {
                        customerWithChanges.setId(id);
                        return customerRepository.save(customerWithChanges);
                    });
            return customerModelAssembler.toModel(updatedCustomer);
        }
    }

    public ResponseEntity<EntityModel<Customer>> deleteCustomer(Long id){
        if (!customerRepository.findById(id).isPresent()) {
            throw new CustomerDoesNotExistException(id);
        } else {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
