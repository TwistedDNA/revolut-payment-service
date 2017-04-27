package transfer;


import entities.Account;
import entities.AccountBalance;
import exceptions.NotEnoughBalanceForOperationException;
import exceptions.TransactionValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class ValidatedTransferTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionOnMissingSourceAccount() {
        thrown.expect(TransactionValidationException.class);
        thrown.expectMessage("Transfer source account cannot be found!");
        ValidatedTransfer validatedTransfer = withoutSourceAccount();
    }

    private ValidatedTransfer withoutSourceAccount() {
        return new ValidatedTransfer(null, null, null);
    }

    @Test
    public void shouldThrowExceptionOnMissingDestinationAccount() {
        thrown.expect(TransactionValidationException.class);
        thrown.expectMessage("Transfer destination account cannot be found!");
        ValidatedTransfer validatedTransfer = withoutDestinationAccount();
    }

    private ValidatedTransfer withoutDestinationAccount() {
        return new ValidatedTransfer(new Account(null, null), null, null);
    }

    @Test
    public void shouldThrowExceptionOnMissingBalanceChange() {
        thrown.expect(TransactionValidationException.class);
        thrown.expectMessage("Transfer balance change cannot be empty!");
        ValidatedTransfer
            validatedTransfer =
            withoutBalanceChange();
    }

    private ValidatedTransfer withoutBalanceChange() {
        return new ValidatedTransfer(new Account(null, null), new Account(null, null), null);
    }

    @Test
    public void shouldThrowExceptionOnNegetiveBalanceChange() {
        thrown.expect(TransactionValidationException.class);
        thrown.expectMessage("Transfer balance change cannot be negetive!");
        ValidatedTransfer
            validatedTransfer =
            withNegetiveTransferBalance();
    }

    private ValidatedTransfer withNegetiveTransferBalance() {
        return new ValidatedTransfer(new Account(null, null), new Account(null, null),
                                     new BalanceChange(new Long(-5), new Long(0)));
    }

    @Test
    public void shouldThrowExceptionWhenInsuficcientSourceAccountBalance() {
        thrown.expect(NotEnoughBalanceForOperationException.class);
        ValidatedTransfer
            validatedTransfer =
            withInsuficientSourceAccountBalance();
    }

    private ValidatedTransfer withInsuficientSourceAccountBalance() {
        return new ValidatedTransfer(new Account(null, new AccountBalance(new Long(3), new Long(0))),
                                     new Account(null, null),
                                     new BalanceChange(new Long(5), new Long(0)));
    }
}