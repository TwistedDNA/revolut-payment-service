package net.twisteddna.exceptions;

/**
 * Created by Maksym_Mazur on 4/27/2017.
 */
public class NotEnoughBalanceForOperationException extends IllegalArgumentException {

    public NotEnoughBalanceForOperationException(String s) {
        super(s);
    }

    public NotEnoughBalanceForOperationException() {
        super("Not enough balance on net.twisteddna.account for operation!");
    }
}
