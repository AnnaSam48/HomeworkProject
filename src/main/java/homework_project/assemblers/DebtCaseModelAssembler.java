package homework_project.assemblers;

import homework_project.controllers.DebtCaseController;
import homework_project.models.DebtCase;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DebtCaseModelAssembler implements RepresentationModelAssembler<DebtCase, EntityModel<DebtCase>> {
    @Override
    public EntityModel<DebtCase> toModel(DebtCase debtCase) {
        return EntityModel.of(debtCase,
                WebMvcLinkBuilder.linkTo(methodOn(DebtCaseController.class).getDebtCaseById(debtCase.getCaseId())).withSelfRel(),
                linkTo(methodOn(DebtCaseController.class).getAllDebtCases()).withRel("cases"));
    }
}
