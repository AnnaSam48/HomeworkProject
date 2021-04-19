package homework_project.controllers;

import homework_project.assemblers.DebtCaseModelAssembler;
import homework_project.models.entities.DebtCase;
import homework_project.models.dtos.DebtCaseDTO;
import homework_project.services.DebtCaseService;
import homework_project.utils.DTOModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class DebtCaseController {

    @Autowired
    DebtCaseService debtCaseService;
    @Autowired
    DebtCaseModelAssembler debtCaseModelAssembler;

    @GetMapping(value = "/cases")
    public CollectionModel<DebtCaseDTO> getAllDebtCases(){
         return CollectionModel.of(debtCaseService.allCases(),
                linkTo(methodOn(DebtCaseController.class).getAllDebtCases()).withSelfRel());
    }

    @GetMapping(value = "/cases/{caseId}")
    public DebtCaseDTO getDebtCaseById(@PathVariable Long caseId){
        return debtCaseModelAssembler.toModel(debtCaseService.debtCaseFoundById(caseId));
    }


    @GetMapping(value = "/cases/customer/{customerId}")
    public CollectionModel<DebtCaseDTO> getDebtCaseListByCustomerId(@PathVariable Long customerId){
        return CollectionModel.of(debtCaseService.allCasesFoundByCustomerId(customerId),
                linkTo(methodOn(DebtCaseController.class).getDebtCaseListByCustomerId(customerId)).withSelfRel());
    }

    @PostMapping(value = "/cases")
    public ResponseEntity<DebtCaseDTO> createNewDebtCase(@RequestBody @DTOModel(DebtCaseDTO.class)DebtCase newDebtCase){
        return ResponseEntity.created(debtCaseService.createDebtCase(newDebtCase)
                .getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(debtCaseService.createDebtCase(newDebtCase));
    }

    @PutMapping(value = "/cases/editDebt/{caseId}")
    public ResponseEntity<DebtCaseDTO> editCase(@RequestBody @DTOModel(DebtCaseDTO.class)DebtCase editedDebtCase, @PathVariable Long caseId){
            return ResponseEntity.created(debtCaseService.updateDebtCase(editedDebtCase, caseId)
            .getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(debtCaseService.updateDebtCase(editedDebtCase, caseId));
    }

    @DeleteMapping(value = "/cases/deleteDebt/{caseId}")
    public ResponseEntity<DebtCaseDTO> deleteCase(@PathVariable Long caseId){
        return debtCaseService.deleteCase(caseId);
    }
}
