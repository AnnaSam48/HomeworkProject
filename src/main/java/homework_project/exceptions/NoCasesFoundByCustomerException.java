package homework_project.exceptions;

public class NoCasesFoundByCustomerException extends RuntimeException{
    public NoCasesFoundByCustomerException(Long id){
        super("There are no cases for customer with id: " + id + ".");
    }
}
