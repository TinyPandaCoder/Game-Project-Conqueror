
package exceptions;

public class NotEnoughGoldException extends Exception  {

    public NotEnoughGoldException() {
    }

    public NotEnoughGoldException(String message) {
        super(message);
    }
    
}
