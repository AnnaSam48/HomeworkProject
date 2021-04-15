package homework_project.controllers;

import homework_project.assemblers.CustomerModelAssembler;
import homework_project.dataSetters.DataSetters;
import homework_project.exceptions.CustomerDoesNotExistException;
import homework_project.models.Customer;
import homework_project.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerModelAssembler customerModelAssembler;

    @GetMapping(value = "/customers")
    public CollectionModel<EntityModel<Customer>> findAllCustomers() {
        List<EntityModel<Customer>> allCustomers = customerRepository.findAll().stream()
                .map(customerModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(allCustomers,
                linkTo(methodOn(CustomerController.class).findAllCustomers()).withSelfRel());
    }

    @GetMapping(value = "/customers/{id}")
    public EntityModel<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerDoesNotExistException(id));
        return customerModelAssembler.toModel(customer);
    }

    @PostMapping(value = "/customers")
    @ResponseBody
    public ResponseEntity<EntityModel<Customer>> createNewCustomer(@RequestBody Customer newCustomer) {
        EntityModel<Customer> customerEntityModel = customerModelAssembler.toModel(customerRepository.save(newCustomer));
        return ResponseEntity.created(customerEntityModel
                .getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(customerEntityModel);
    }

    @PutMapping(value = "/customers/edit/{id}")
    public ResponseEntity<EntityModel<Customer>> editCustomer(@RequestBody Customer editedCustomer, @PathVariable Long id) {
        if (!customerRepository.findById(id).isPresent()) {
            throw new CustomerDoesNotExistException(id);
        } else {
            Customer updatedCustomer = customerRepository.findById(id)
                    .map(customer -> {
                        DataSetters.setDataInCustomer(customer,editedCustomer);
                        return customerRepository.save(customer);
                    }).orElseGet(() -> {
                        editedCustomer.setId(id);
                        return customerRepository.save(editedCustomer);
                    });

            EntityModel<Customer> customerEntityModel = customerModelAssembler.toModel(updatedCustomer);
            return ResponseEntity.created(customerEntityModel
                    .getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(customerEntityModel);
        }
    }

    @DeleteMapping(value = "/customers/delete/{id}")
    public ResponseEntity<EntityModel<Customer>> customerToDelete(@PathVariable Long caseId) {
        if (!customerRepository.findById(caseId).isPresent()) {
            throw new CustomerDoesNotExistException(caseId);
        } else {
            customerRepository.deleteById(caseId);
            return ResponseEntity.noContent().build();
        }
    }
}
