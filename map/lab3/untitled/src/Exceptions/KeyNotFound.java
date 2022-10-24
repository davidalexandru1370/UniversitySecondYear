package Exceptions;

public class KeyNotFound extends RuntimeException {
    public KeyNotFound(String message){
        super(message);
    }
}
