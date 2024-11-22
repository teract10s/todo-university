package test.task.todolist.exception;

public class WrongDeadlineException extends RuntimeException {
    public WrongDeadlineException(String message) {
        super(message);
    }

    public WrongDeadlineException(String message, Throwable cause) {
        super(message, cause);
    }
}
