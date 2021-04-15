package homework_project.controllers;

import homework_project.assemblers.CustomerModelAssembler;
import homework_project.services.CustomerService;
import homework_project.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerModelAssembler customerModelAssembler;

    @GetMapping(value = "/customers")
    public CollectionModel<EntityModel<Customer>> findAllCustomers() {
        return CollectionModel.of(customerService.allCustomers(),
                linkTo(methodOn(CustomerController.class).findAllCustomers()).withSelfRel());
    }

    @GetMapping(value = "/customers/{id}")
    public EntityModel<Customer> getCustomerById(@PathVariable Long id) {
        return customerModelAssembler.toModel(customerService.customerFoundById(id));
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<EntityModel<Customer>> createNewCustomer(@RequestBody Customer newCustomer) {
        return ResponseEntity.created(customerService.createCustomer(newCustomer)
                .getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(customerService.createCustomer(newCustomer));
    }

    @PutMapping(value = "/customers/edit/{id}")
    public ResponseEntity<EntityModel<Customer>> editCustomer(@RequestBody Customer editedCustomer, @PathVariable Long id) {
            return ResponseEntity.created(customerService.updateCustomer(editedCustomer,id)
                    .getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(customerService.updateCustomer(editedCustomer,id));
    }

    @DeleteMapping(value = "/customers/delete/{id}")
    public ResponseEntity<EntityModel<Customer>> customerToDelete(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
