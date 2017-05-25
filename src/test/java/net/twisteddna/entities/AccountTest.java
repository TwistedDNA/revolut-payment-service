package net.twisteddna.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.twisteddna.exceptions.NotEnoughBalanceForOperationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Maksym_Mazur on 4/26/2017.
 */
public class AccountTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTrueOnEnoughBalance() {
        Account account = plainAccount();

        assertTrue(account.isEnoughForTransaction(BigDecimal.valueOf(1.1)));
    }

    @Test
    public void shouldThrowExceptionOnNotEnoughBalance() {
        Account account = plainAccount();
        thrown.expect(NotEnoughBalanceForOperationException.class);

        account.isEnoughForTransaction(BigDecimal.valueOf(6));
    }

    @Test
    public void shouldProperlySubstractAccountBalance() {
        Account account = plainAccount();

        account.substract(BigDecimal.valueOf(1.1));

        assertEquals(BigDecimal.valueOf(4.1).round(MathContext.DECIMAL32), account.getAccountBalance());

    }

    @Test
    public void shouldProperlyBenefitAccountBalance() {
        Account account = plainAccount();

        account.benefit(BigDecimal.valueOf(1.1));

        assertEquals(BigDecimal.valueOf(6.3).round(MathContext.DECIMAL32), account.getAccountBalance());

    }

    private Account plainAccount() {
        return new Account(4L, BigDecimal.valueOf(5.2));
    }


}