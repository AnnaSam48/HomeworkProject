package homework_project.services;

import homework_project.assemblers.DebtCaseModelAssembler;
import homework_project.data_setters.DataSetters;
import homework_project.exceptions.CaseWithThisIdNotFoundException;
import homework_project.exceptions.NoCasesFoundByCustomerException;
import homework_project.models.entities.DebtCase;
import homework_project.models.dtos.CustomerDTO;
import homework_project.models.dtos.DebtCaseDTO;
import homework_project.repositories.DebtCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class DebtCaseService {

    @Autowired
    DebtCaseRepository debtCaseRepository;
    @Autowired
    DebtCaseModelAssembler debtCaseModelAssembler;

    public List<DebtCaseDTO> allCases(){
        return debtCaseRepository.findAll().stream()
                .map(debtCaseModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    public DebtCase debtCaseFoundById(Long id){
        return debtCaseRepository.findById(id)
                .orElseThrow(() -> new CaseWithThisIdNotFoundException(id));
    }

    public List<DebtCaseDTO> allCasesFoundByCustomerId(Long customerId){
        if(debtCaseRepository.findByCustomerId(customerId).isEmpty()){
            throw new NoCasesFoundByCustomerException(customerId);
        }
        return debtCaseRepository.findByCustomerId(customerId).stream()
                .map(debtCaseModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    public DebtCaseDTO createDebtCase(DebtCase newCase){
        return debtCaseModelAssembler.toModel(debtCaseRepository.save(newCase));
    }

    public DebtCaseDTO updateDebtCase(DebtCase debtCaseWithChanges, Long id) {
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

    public ResponseEntity<DebtCaseDTO> deleteCase(Long id){
        if(!debtCaseRepository.findById(id).isPresent()){
            throw new CaseWithThisIdNotFoundException(id);
        }else {
            debtCaseRepository.deleteById(id);
            ResponseEntity.ok(CustomerDTO.class);
            return ResponseEntity.noContent().build();
        }
    }
}
