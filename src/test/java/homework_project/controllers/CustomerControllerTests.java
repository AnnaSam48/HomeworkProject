package homework_project.controllers;

import homework_project.assemblers.CustomerModelAssembler;
import homework_project.models.Customer;
import homework_project.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CustomerControllerUnitTests {

    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    CustomerModelAssembler customerModelAssembler;

    @BeforeEach
    void setUp() {

    }

    @Test
    @Disabled
    public Customer getCustomerTest(){
        fail();
        return null;
    }

    @Test
    @Disabled
    public void createCustomerTest() throws Exception {
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(getNewCustomer());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customers")
                .accept(MediaType.APPLICATION_JSON).content(JSONExampleForNewCustomer)
                .contentType(MediaType.APPLICATION_JSON);

       // MvcResult result = mockMvc.perform(requestBuilder).andReturn();
       // MockHttpServletResponse response = result.getResponse();
       // assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    private Customer getNewCustomer() {
        return new Customer(2L, "Blane", "Barrad", "Bulgaria", "blane@blacksmiths.bg",
                "somePassword", null);
    }

    private final String JSONExampleForNewCustomer = "{\"firstName\":\"Blane\",\"surname\":\"Barrad\"," +
            "\"country\":\"Bulgaria\",\"email\":\"blane@blacksmiths.bg\",\"password\": \"somePassword\"}";
}