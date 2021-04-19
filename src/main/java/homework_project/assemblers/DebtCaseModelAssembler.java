package homework_project.assemblers;

import homework_project.controllers.CustomerController;
import homework_project.controllers.DebtCaseController;
import homework_project.models.entities.DebtCase;
import homework_project.models.dtos.DebtCaseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DebtCaseModelAssembler implements RepresentationModelAssembler<DebtCase, DebtCaseDTO> {
    @Override
    public DebtCaseDTO toModel(DebtCase debtCase) {
        ModelMapper debtCaseModelMapper = new ModelMapper();
        DebtCaseDTO debtCaseDTO = debtCaseModelMapper.map(debtCase, DebtCaseDTO.class);
        debtCaseDTO.add(WebMvcLinkBuilder.linkTo(methodOn(DebtCaseController.class).getDebtCaseById(debtCase.getCaseId())).withSelfRel(),
                linkTo(methodOn(DebtCaseController.class).getAllDebtCases()).withRel("cases"),
                linkTo(methodOn(CustomerController.class).getCustomerById(debtCase.getCustomerId())).withRel("customers"));

        return debtCaseDTO;
    }
}
