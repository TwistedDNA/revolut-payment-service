package entities;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    //TODO cover all Account public methods except getters and setters

    private Account plainAccount() {
        return new Account(new Long(4), new AccountBalance(new Long(5), new Long(2)));
    }


}