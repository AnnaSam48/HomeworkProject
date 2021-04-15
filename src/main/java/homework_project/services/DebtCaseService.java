package homework_project.services;

import homework_project.assemblers.DebtCaseModelAssembler;
import homework_project.data_setters.DataSetters;
import homework_project.exceptions.CaseWithThisIdNotFoundException;
import homework_project.exceptions.NoCasesFoundByCustomerException;
import homework_project.models.DebtCase;
import homework_project.repositories.DebtCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DebtCaseService {

    @Autowired
    DebtCaseRepository debtCaseRepository;
    @Autowired
    DebtCaseModelAssembler debtCaseModelAssembler;

    public List<EntityModel<DebtCase>> allCases(){
        return debtCaseRepository.findAll().stream()
                .map(debtCaseModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    public DebtCase debtCaseFoundById(Long id){
        return debtCaseRepository.findById(id)
                .orElseThrow(() -> new CaseWithThisIdNotFoundException(id));
    }

    public List<EntityModel<DebtCase>> allCasesFoundByCustomerId(Long customerId){
        if(debtCaseRepository.findByCustomerId(customerId)==null){
            throw new NoCasesFoundByCustomerException(customerId);
        }
        return debtCaseRepository.findByCustomerId(customerId).stream()
                .map(debtCaseModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    public EntityModel<DebtCase> createDebtCase(DebtCase newCase){
        return debtCaseModelAssembler.toModel(debtCaseRepository.save(newCase));
    }

    public EntityModel<DebtCase> updateDebtCase(DebtCase debtCaseWithChanges, Long id) {
        if (!debtCaseRepository.findById(id).isPresent()) {
            throw new CaseWithThisIdNotFoundException(id);
        } else {
            DebtCase existingDebtCase = debtCaseRepository.findById(id)
                    .map(debtCase -> {
                        DataSetters.setDataInDebtCase(debtCase, debtCaseWithChanges);
                        return debtCaseRepository.save(debtCase);
                    }).orElseGet(() -> {
                        debtCaseWithChanges.setCaseId(id);
                        return debtCaseRepository.save(debtCaseWithChanges);
                    });
            return debtCaseModelAssembler.toModel(existingDebtCase);
        }
    }

    public ResponseEntity<EntityModel<DebtCase>> deleteCase(Long id){
        if(!debtCaseRepository.findById(id).isPresent()){
            throw new CaseWithThisIdNotFoundException(id);
        }else {
            debtCaseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
