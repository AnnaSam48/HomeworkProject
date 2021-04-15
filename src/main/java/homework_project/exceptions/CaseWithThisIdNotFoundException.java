package homework_project.exceptions;

public class CaseWithThisIdNotFoundException extends RuntimeException{

     public CaseWithThisIdNotFoundException(Long id){
        super("There is no case with id: " + id + ".");
    }
}
