package HomeworkProject.controllers;

import HomeworkProject.exceptions.CaseWithThisIdNotFound;
import HomeworkProject.exceptions.NoCasesFoundByCustomer;
import HomeworkProject.models.DebtCase;
import HomeworkProject.repositories.CustomerRepository;
import HomeworkProject.repositories.DebtCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cases")
public class DebtCaseController {

    @Autowired
    DebtCaseRepository debtCaseRepository;

    @GetMapping(value = "/", produces = "application/json")
    List<DebtCase> getAllDebtCases(){
        return debtCaseRepository.findAll();
    }

    @PostMapping(value = "/")
    @ResponseBody
    DebtCase postNewDebtCase(@RequestBody DebtCase newDebtCase){
        return debtCaseRepository.save(newDebtCase);
    }

    @GetMapping(value = "/{caseId}", produces = "application/json")
    DebtCase getDebtCaseById(@PathVariable Long caseId){
        return debtCaseRepository.findById(caseId).orElseThrow(() -> new CaseWithThisIdNotFound(caseId));
    }

    @GetMapping(value = "/{customerId}", produces = "application/json")
    List<DebtCase> getDebtCaseListByCustomerId(@PathVariable Long customerId){
        if(debtCaseRepository.findByCustomer(customerId)==null){
           throw new NoCasesFoundByCustomer(customerId);
        }
        return debtCaseRepository.findByCustomer(customerId);
    }

    @PutMapping(value = "/editDebt/{caseId}", produces= "application/json")
    DebtCase editCase(@RequestBody DebtCase editedDebtCase, @PathVariable Long caseId){
        return debtCaseRepository.findById(caseId)
                .map(debtCase -> {
                    debtCase.setDebtor(editedDebtCase.getDebtor());
                    debtCase.setAmount(editedDebtCase.getAmount());
                    debtCase.setCurrency(editedDebtCase.getCurrency());
                    debtCase.setDueDate(editedDebtCase.getDueDate());
                    return debtCaseRepository.save(debtCase);
                }).orElseGet(() -> {
                    editedDebtCase.setCaseId(caseId);
                    return debtCaseRepository.save(editedDebtCase);
                });
    }

    @DeleteMapping(value = "/deleteDebt/{caseId}", produces = "application/json")
    void caseToDelete(@PathVariable Long caseId){
        debtCaseRepository.deleteById(caseId);
    }

}
