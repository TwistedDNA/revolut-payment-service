package entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import exceptions.NotEnoughBalanceForOperationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import transfer.BalanceChange;

/**
 * Created by Maksym_Mazur on 4/26/2017.
 */
public class AccountTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionOnNullBalanceChange() {
        Account account = plainAccount();
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("BalanceChange object is not valid!");

        account.isEnoughForTransaction(null);
    }

    @Test
    public void shouldReturnTrueOnEnoughBalance() {
        Account account = plainAccount();
        BalanceChange balanceChange = new BalanceChange(new Long(1), new Long(1));

        assertTrue(account.isEnoughForTransaction(balanceChange));
    }

    @Test
    public void shouldThrowExceptionOnNotEnoughBalance() {
        Account account = plainAccount();
        thrown.expect(NotEnoughBalanceForOperationException.class);
        BalanceChange balanceChange = new BalanceChange(new Long(5), new Long(5));

        account.isEnoughForTransaction(balanceChange);
    }

    @Test
    public void shouldProperlySubstractAccountBalance() {
        Account account = plainAccount();
        BalanceChange balanceToSubstract = new BalanceChange(new Long(1), new Long(1));
        AccountBalance expectedBalance = new AccountBalance(new Long(4), new Long(1));

        account.substract(balanceToSubstract);

        assertEquals(expectedBalance, account.getAccountBalance());

    }

    @Test
    public void shouldProperlyBenefitAccountBalance() {
        Account account = plainAccount();
        BalanceChange balanceToSubstract = new BalanceChange(new Long(1), new Long(1));
        AccountBalance expectedBalance = new AccountBalance(new Long(6), new Long(3));

        account.benefit(balanceToSubstract);

        assertEquals(expectedBalance, account.getAccountBalance());

    }

    private Account plainAccount() {
        return new Account(new Long(4), new AccountBalance(new Long(5), new Long(2)));
    }


}