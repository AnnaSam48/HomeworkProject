package homework_project.controllers;

import homework_project.assemblers.DebtCaseModelAssembler;
import homework_project.dataSetters.DataSetters;
import homework_project.exceptions.CaseWithThisIdNotFoundException;
import homework_project.exceptions.NoCasesFoundByCustomerException;
import homework_project.models.Customer;
import homework_project.models.DebtCase;
import homework_project.repositories.DebtCaseRepository;
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
public class DebtCaseController {

    @Autowired
    DebtCaseRepository debtCaseRepository;
    @Autowired
    DebtCaseModelAssembler debtCaseModelAssembler;

    @GetMapping(value = "/cases")
    public CollectionModel<EntityModel<DebtCase>> getAllDebtCases(){
        List<EntityModel<DebtCase>> allCases = debtCaseRepository.findAll().stream()
                .map(debtCaseModelAssembler::toModel)
                .collect(Collectors.toList());
         return CollectionModel.of(allCases,
                linkTo(methodOn(DebtCaseController.class).getAllDebtCases()).withSelfRel());
    }

    @GetMapping(value = "/cases/{caseId}")
    public EntityModel<DebtCase> getDebtCaseById(@PathVariable Long caseId){
        DebtCase debtCase = debtCaseRepository.findById(caseId)
                .orElseThrow(() -> new CaseWithThisIdNotFoundException(caseId));
        return debtCaseModelAssembler.toModel(debtCase);
    }

    @GetMapping(value = "/cases/{customerId}")
    public CollectionModel<EntityModel<DebtCase>> getDebtCaseListByCustomerId(@PathVariable Long customerId){
        if(debtCaseRepository.findByCustomer(customerId)==null){
            throw new NoCasesFoundByCustomerException(customerId);
        }
        List<EntityModel<DebtCase>> allCasesByCustomer = debtCaseRepository.findByCustomer(customerId).stream()
                .map(debtCaseModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(allCasesByCustomer,
                linkTo(methodOn(DebtCaseController.class).getDebtCaseListByCustomerId(customerId)).withSelfRel());
    }

    @PostMapping(value = "/cases")
    @ResponseBody
    public ResponseEntity<EntityModel<DebtCase>> createNewDebtCase(@RequestBody DebtCase newDebtCase){
        EntityModel<DebtCase> debtCaseEntityModel = debtCaseModelAssembler.toModel(debtCaseRepository.save(newDebtCase));
        return ResponseEntity.created(debtCaseEntityModel
                .getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(debtCaseEntityModel);
    }

    @PutMapping(value = "/cases/editDebt/{caseId}")
    public ResponseEntity<EntityModel<DebtCase>> editCase(@RequestBody DebtCase editedDebtCase, @PathVariable Long caseId){
        if(!debtCaseRepository.findById(caseId).isPresent()) {
            throw new CaseWithThisIdNotFoundException(caseId);
        } else {
            DebtCase existingDebtcase = debtCaseRepository.findById(caseId)
                    .map(debtCase -> {
                        DataSetters.setDataInDebtCase(editedDebtCase, debtCase);
                        return debtCaseRepository.save(debtCase);
                    }).orElseGet(() -> {
                        editedDebtCase.setCaseId(caseId);
                        return debtCaseRepository.save(editedDebtCase);
                    });
            EntityModel<DebtCase> debtCaseEntityModel = debtCaseModelAssembler.toModel(existingDebtcase);
            return ResponseEntity.created(debtCaseEntityModel
            .getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(debtCaseEntityModel);
        }


    }

    @DeleteMapping(value = "/cases/deleteDebt/{caseId}")
    public ResponseEntity<EntityModel<DebtCase>> deleteCase(@PathVariable Long caseId){
        if(!debtCaseRepository.findById(caseId).isPresent()){
            throw new CaseWithThisIdNotFoundException(caseId);
        }else {
        debtCaseRepository.deleteById(caseId);
        return ResponseEntity.noContent().build();
        }
    }
}
