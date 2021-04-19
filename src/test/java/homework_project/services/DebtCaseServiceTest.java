package homework_project.services;

import homework_project.assemblers.DebtCaseModelAssembler;
import homework_project.exceptions.CaseWithThisIdNotFoundException;
import homework_project.exceptions.NoCasesFoundByCustomerException;
import homework_project.models.entities.Customer;
import homework_project.models.entities.DebtCase;
import homework_project.repositories.DebtCaseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DebtCaseServiceTest {

    @InjectMocks
    private DebtCaseService debtCaseService;
    @Mock
    DebtCaseRepository debtCaseRepository;
    @Mock
    DebtCaseModelAssembler debtCaseModelAssembler;

    @Test
    void getAllDebtCasesTest() {
        List<DebtCase> allCases = getAllCases();
        when(debtCaseRepository.findAll()).thenReturn(allCases);
        debtCaseService.allCases();
        Mockito.verify(debtCaseRepository, times(1)).findAll();
        Assertions.assertEquals(3, debtCaseService.allCases().size());
    }

    @Test
    void getDebtCaseByIdTest() {
        DebtCase debtCase = getDebtCase();
        when(debtCaseRepository.findById(anyLong())).thenReturn(Optional.of(debtCase));
        debtCaseService.debtCaseFoundById(20L);
        Mockito.verify(debtCaseRepository, times(1)).findById(20L);
        Assertions.assertEquals(debtCaseService.debtCaseFoundById(20L), debtCase);
    }

    @Test
    void getDebtCaseByCustomerIdTest() {
        List<DebtCase> caseList = new ArrayList<>();
        DebtCase debtCase = getDebtCase();
        DebtCase debtCaseZ = getCaseA();
        DebtCase debtCaseY = getCaseA();
        debtCaseY.setCustomerId(getAnotherCustomer().getId());
        caseList.add(debtCase);
        caseList.add(debtCaseZ);
        when(debtCaseRepository.findByCustomerId(101L)).thenReturn(caseList);
        debtCaseService.allCasesFoundByCustomerId(101L);
        Mockito.verify(debtCaseRepository, times(2)).findByCustomerId(101L);
        Assertions.assertEquals(2, debtCaseService.allCasesFoundByCustomerId(101L).size());
        Assertions.assertEquals(debtCase.getCustomerId(), debtCaseZ.getCustomerId());
        Assertions.assertNotEquals(debtCase.getCustomerId(), debtCaseY.getCustomerId());
    }


    @Test
    void createDebtCaseTest() {
        DebtCase newCase = getDebtCase();
        when(debtCaseRepository.save(any(DebtCase.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);
        debtCaseService.createDebtCase(newCase);
        Mockito.verify(debtCaseRepository,times(1)).save(newCase);
    }

    @Test
    void updateDebtCaseWithExistingIdTest(){
        DebtCase old = getDebtCase();
        DebtCase updated = getCaseA();
        when(debtCaseRepository.findById(anyLong())).thenReturn(Optional.of(old));
        debtCaseService.updateDebtCase(updated, old.getCaseId());
        Mockito.verify(debtCaseRepository,times(2)).save(updated);
        Assertions.assertEquals(old.getCurrency(), updated.getCurrency());
        Assertions.assertEquals(old.getCaseId(), updated.getCaseId());

    }

    @Test
    void caseWithIdIsNotFoundExceptionTest() {
        when(debtCaseRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(CaseWithThisIdNotFoundException.class, () -> debtCaseService.debtCaseFoundById(111L));
    }

    @Test
    void caseNotFoundByCustomerExceptionTest() {
        when(debtCaseRepository.findByCustomerId(anyLong())).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NoCasesFoundByCustomerException.class, () -> debtCaseService.allCasesFoundByCustomerId(111L));
    }

    @Test
    void deleteDebtCaseWithNoExistingIdTest(){
        DebtCase old = getDebtCase();
        DebtCase updated = getCaseA();
        when(debtCaseRepository.findById(anyLong())).thenReturn(Optional.empty());
        debtCaseService.updateDebtCase(updated, old.getCaseId());
        Mockito.verify(debtCaseRepository,times(1)).save(updated);
        Assertions.assertEquals(old.getCaseId(), updated.getCaseId());
    }

    public DebtCase getDebtCase(){
        DebtCase testCase = new DebtCase();
        testCase.setCustomerId(getCustomer().getId());
        testCase.setCaseId(20L);
        testCase.setAmount(BigDecimal.valueOf(19.23));
        testCase.setCurrency("Gold nuggets");
        testCase.setDueDate(new Date(99999999993200000L));
        return testCase;
    }

    public DebtCase getCaseA(){
        DebtCase caseA = new DebtCase();
        caseA.setCaseId(1L);
        Customer customerB = getCustomer();
        customerB.setId(101L);
        customerB.setFirstName("Donny");
        caseA.setCustomerId(customerB.getId());
        caseA.setCurrency("Silver Buttons");
        caseA.setAmount(BigDecimal.valueOf(15.7));
        return caseA;
    }

    public List<DebtCase> getAllCases(){
        DebtCase caseB = new DebtCase();
        caseB.setCustomerId(getCustomer().getId());
        caseB.setCaseId(2L);
        caseB.setCurrency("Swan Feathers");
        caseB.setAmount(BigDecimal.valueOf(2.5));
        caseB.setDueDate(new Date(8765544565432L));
        List<DebtCase> allCases = new ArrayList<>();
        allCases.add(getDebtCase());
        allCases.add(caseB);
        allCases.add(getCaseA());
        return allCases;
    }

    public Customer getCustomer(){
        Customer customer = new Customer();
        customer.setId(101L);
        customer.setFirstName("Ziggy");
        customer.setLastName("Stardust");
        customer.setEmail("someone@somewhere.con");
        customer.setCountry("Nowhere Land");
        customer.setPassword("SpidersFromMars");
        return customer;
    }

    private Customer getAnotherCustomer(){
        Customer customer = new Customer();
        customer.setId(102L);
        customer.setFirstName("Lazarus");
        customer.setLastName("Digger");
        customer.setEmail("lazy@somewhere.con");
        customer.setCountry("Nowhere Land");
        customer.setPassword("Digging");
        return customer;
    }
}
