package homework_project.data_setters;

import homework_project.models.entities.Customer;
import homework_project.models.entities.DebtCase;

public class DataSetters {

    public static void setDataInCustomer(Customer customer, Customer editedCustomer) {
        customer.setFirstName(editedCustomer.getFirstName());
        customer.setLastName(editedCustomer.getLastName());
        customer.setCountry(editedCustomer.getCountry());
        customer.setEmail(editedCustomer.getEmail());
        customer.setPassword(editedCustomer.getPassword());
    }

    public static void setDataInDebtCase(DebtCase debtCase, DebtCase editedDebtCase) {
        debtCase.setCustomerId(editedDebtCase.getCustomerId());
        debtCase.setAmount(editedDebtCase.getAmount());
        debtCase.setCurrency(editedDebtCase.getCurrency());
        debtCase.setDueDate(editedDebtCase.getDueDate());
    }
}
