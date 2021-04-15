package homework_project.dataSetters;

import homework_project.models.Customer;
import homework_project.models.DebtCase;

public class DataSetters {

    public static void setDataInCustomer(Customer customer, Customer editedCustomer) {
        customer.setFirstName(editedCustomer.getFirstName());
        customer.setSurname(editedCustomer.getSurname());
        customer.setCountry(editedCustomer.getCountry());
        customer.setEmail(editedCustomer.getEmail());
        customer.setCustomersDebtCases(editedCustomer.getCustomersDebtCases());
    }

    public static void setDataInDebtCase(DebtCase editedDebtCase, DebtCase debtCase) {
        debtCase.setDebtor(editedDebtCase.getDebtor());
        debtCase.setAmount(editedDebtCase.getAmount());
        debtCase.setCurrency(editedDebtCase.getCurrency());
        debtCase.setDueDate(editedDebtCase.getDueDate());
    }
}
