package HomeworkProject.exceptions;

public class CaseWithThisIdNotFound extends RuntimeException{

     public CaseWithThisIdNotFound(Long id){
        super("There is no case with id: " + id);
    }
}
