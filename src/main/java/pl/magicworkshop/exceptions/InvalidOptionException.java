package pl.magicworkshop.exceptions;

public class InvalidOptionException extends RuntimeException {

    public InvalidOptionException(){
        super("Nie ma takiej opcji");
    }
}
