package Exceptions;

public class ParkingException extends RuntimeException {
    public ParkingException(String msg) {
        super("Something went wrong reason: "+msg);
    }
}
