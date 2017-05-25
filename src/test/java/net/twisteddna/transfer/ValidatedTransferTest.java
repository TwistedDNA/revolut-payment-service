package net.twisteddna.transfer;


import net.twisteddna.entities.Account;
import net.twisteddna.exceptions.NotEnoughBalanceForOperationException;
import net.twisteddna.exceptions.TransactionValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class ValidatedTransferTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public void shouldThrowExceptionOnMissingSourceAccount() {
        thrown.expect(TransactionValidationException.class);
        thrown.expectMessage("Transfer source account cannot be found!");
        withoutSourceAccount();
    }

    private ValidatedTransfer withoutSourceAccount() {
        return new ValidatedTransfer(null, null, null);
    }

    @Test
    public void shouldThrowExceptionOnMissingDestinationAccount() {
        thrown.expect(TransactionValidationException.class);
        thrown.expectMessage("Transfer destination account cannot be found!");
        withoutDestinationAccount();
    }

    private ValidatedTransfer withoutDestinationAccount() {
        return new ValidatedTransfer(new Account(null, null), null, null);
    }

    private ValidatedTransfer withoutBalanceChange() {
        return new ValidatedTransfer(new Account(null, null), new Account(null, null), null);
    }

    @Test
    public void shouldThrowExceptionOnNegetiveBalanceChange() {
        thrown.expect(TransactionValidationException.class);
        thrown.expectMessage("Transfer balance change cannot be negetive!");
        withNegetiveTransferBalance();
    }

    private ValidatedTransfer withNegetiveTransferBalance() {
        return new ValidatedTransfer(new Account(null, null), new Account(null, null),
                                     new BigDecimal(-5.0));
    }

    @Test
    public void shouldThrowExceptionWhenInsuficcientSourceAccountBalance() {
        thrown.expect(NotEnoughBalanceForOperationException.class);
        withInsuficientSourceAccountBalance();
    }

    private ValidatedTransfer withInsuficientSourceAccountBalance() {
        return new ValidatedTransfer(new Account(null, new BigDecimal(3.0)),
                                     new Account(null, null),
                                     new BigDecimal(5.0));
    }
}