package homework_project.services;


import homework_project.assemblers.CustomerModelAssembler;
import homework_project.exceptions.CustomerDoesNotExistException;
import homework_project.models.entities.Customer;
import homework_project.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerModelAssembler fullCustomerModelAssembler;

    @Test
    void getAllCustomersTest() {
        List<Customer> allCustomers = getMultipleCustomers();
        when(customerRepository.findAll()).thenReturn(allCustomers);
        customerService.allCustomers();
        Mockito.verify(customerRepository, times(1)).findAll();
        Assertions.assertEquals(3, customerService.allCustomers().size());
    }

    @Test
    void getCustomerByIdTest() {
        Customer customer = getCustomer();
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        customerService.customerFoundById(101L);
        Mockito.verify(customerRepository, times(2)).findById(101L);
        Assertions.assertEquals(customerService.customerFoundById(101L), customer);
    }

    @Test
    void createCustomerTest() {
        Customer newCustomer = getCustomer();
        when(customerRepository.save(any(Customer.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);
        customerService.createCustomer(newCustomer);
        Mockito.verify(customerRepository,times(1)).save(newCustomer);
    }

    @Test
    void updateExistingCustomerTest() {
        Customer old = getCustomer();
        Customer updated = getAnotherCustomer();
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(old));
        customerService.updateCustomer(updated, old.getId());
        Mockito.verify(customerRepository,times(2)).save(updated);
        Assertions.assertEquals(old.getFirstName(), updated.getFirstName());
        Assertions.assertEquals(old.getId(), updated.getId());
    }

    @Test
    void updateCustomerWithNewIdTest() {
        Customer old = getCustomer();
        Customer updated = getAnotherCustomer();
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        customerService.updateCustomer(updated, old.getId());
        Mockito.verify(customerRepository,times(1)).save(updated);
    }


    @Test
    void customerNotFoundByIdExceptionTest() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerDoesNotExistException.class, () -> customerService.customerFoundById(20L));
    }

    @Test
    void customerNotFoundForDeletingTest() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerDoesNotExistException.class, ()-> customerService.deleteCustomer(22L));
    }

    private List<Customer> getMultipleCustomers(){
        Customer customerX = new Customer();
        customerX.setId(1L);
        customerX.setFirstName("Fox");
        customerX.setLastName("Mulder");
        Customer customerY = new Customer();
        customerY.setId(2L);
        customerY.setFirstName("Dana");
        customerY.setLastName("Scully");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customerX);
        customerList.add(customerY);
        customerList.add(getCustomer());
        return customerList;
    }

    private Customer getCustomer(){
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
