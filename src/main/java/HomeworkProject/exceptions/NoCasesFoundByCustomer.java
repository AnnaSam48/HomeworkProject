package HomeworkProject.exceptions;

public class NoCasesFoundByCustomer extends RuntimeException{
    public NoCasesFoundByCustomer(Long id){
        super("There is no cases for customer with id: " + id);
    }
}
