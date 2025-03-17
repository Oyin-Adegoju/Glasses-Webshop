package nl.EuniqBrillen.BrillenWebshop.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
