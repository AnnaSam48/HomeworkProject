package homework_project.exceptions;

public class TechnicalError extends RuntimeException {
    public TechnicalError(){
        super("There is technical error.");
    }
}
