package homework_project.services;

import homework_project.assemblers.CustomerModelAssembler;
import homework_project.exceptions.CustomerDoesNotExistException;
import homework_project.models.Customer;
import homework_project.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CustomerServiceTest {

    @Mock
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerModelAssembler customerModelAssembler;

    @BeforeEach
    void setUp() {
        Customer customer = getCustomer();
    }

    @Test
    void customerNotFoundExceptionTest() {
        Customer customer = new Customer();
        customer.setId(102L);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(CustomerDoesNotExistException.class, () -> {
            customerService.customerFoundById(customer.getId());
        });
    }

    private Customer getCustomer(){
        Customer customer = new Customer();
        customer.setId(101L);
        customer.setFirstName("Ziggy");
        customer.setLastName("Stardust");
        customer.setEmail("someone@somewhere.con");
        customer.setCountry("Nowhere Land");
        return customer;
    }
}
