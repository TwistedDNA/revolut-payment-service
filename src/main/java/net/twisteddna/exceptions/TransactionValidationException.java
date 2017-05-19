package net.twisteddna.exceptions;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class TransactionValidationException extends IllegalArgumentException {

    public TransactionValidationException(String s) {
        super(s);
    }
}
