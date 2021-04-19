package homework_project.assemblers;

import homework_project.controllers.CustomerController;
import homework_project.controllers.DebtCaseController;
import homework_project.models.dtos.CustomerDTO;
import homework_project.models.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, CustomerDTO> {

    @Override
    public CustomerDTO toModel(Customer customer) {
        ModelMapper customerModelMapper = new ModelMapper();
        CustomerDTO getCustomerDTO = customerModelMapper.map(customer, CustomerDTO.class);
        getCustomerDTO.add(WebMvcLinkBuilder.linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).findAllCustomers()).withRel("customers"),
                linkTo(methodOn(DebtCaseController.class).getDebtCaseListByCustomerId(customer.getId())).withRel("cases"));

        return getCustomerDTO;
    }
}
