package homework_project.services;

import homework_project.assemblers.CustomerModelAssembler;
import homework_project.data_setters.DataSetters;
import homework_project.exceptions.CustomerDoesNotExistException;
import homework_project.exceptions.TechnicalError;
import homework_project.models.dtos.CustomerDTO;
import homework_project.models.entities.Customer;
import homework_project.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerModelAssembler customerModelAssembler;

    public List<CustomerDTO> allCustomers(){
       return customerRepository.findAll().stream()
                .map(customerModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    public Customer customerFoundById(Long id){
        if(!customerRepository.findById(id).isPresent()){
            throw new CustomerDoesNotExistException(id);
        }
        return customerRepository.findById(id)
                .orElseThrow(TechnicalError::new);
    }

    public CustomerDTO createCustomer(Customer newCustomer){
        return customerModelAssembler.toModel(customerRepository.save(newCustomer));
    }

    public CustomerDTO updateCustomer(Customer customerWithChanges, Long id) {
            Customer updatedCustomer = customerRepository.findById(id)
                    .map(customer -> {
                        DataSetters.setDataInCustomer(customer, customerWithChanges);
                        return customerRepository.save(customer);
                    }).orElseGet(() -> {
                        //creates new customer with given id
                        customerWithChanges.setId(id);
                        return customerRepository.save(customerWithChanges);
                    });
            return customerModelAssembler.toModel(updatedCustomer);
    }

    public ResponseEntity<CustomerDTO> deleteCustomer(Long id){
        if (!customerRepository.findById(id).isPresent()) {
            throw new CustomerDoesNotExistException(id);
        } else {
            customerRepository.deleteById(id);
            ResponseEntity.ok(CustomerDTO.class);
            return ResponseEntity.noContent().build();
        }
    }
}
