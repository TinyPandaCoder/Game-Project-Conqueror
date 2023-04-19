
package exceptions;

public class NotEnoughGoldException extends EmpireException  {

    public NotEnoughGoldException() {
    }

    public NotEnoughGoldException(String message) {
        super(message);
    }
    
}
