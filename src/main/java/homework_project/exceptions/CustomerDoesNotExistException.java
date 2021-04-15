package homework_project.exceptions;

public class CustomerDoesNotExistException extends RuntimeException{
    public CustomerDoesNotExistException(long id){
        super("Customer with id "+ id + " doesn't exist in database.");
    }
}
