package net.twisteddna.entities;

import net.twisteddna.exceptions.NotEnoughBalanceForOperationException;

import java.math.BigDecimal;
import java.math.MathContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by Maksym_Mazur on 4/25/2017.
 */

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal accountBalance;

    public Account(Long id, BigDecimal accountBalance) {
        this.id = id;
        this.accountBalance = (accountBalance == null ? null : accountBalance.round(MathContext.DECIMAL32));
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance.round(MathContext.DECIMAL32);
    }

    public boolean isEnoughForTransaction(BigDecimal balanceChange) throws NotEnoughBalanceForOperationException {

        if (accountBalance.compareTo(balanceChange.round(MathContext.DECIMAL32)) < 0) {
            throw new NotEnoughBalanceForOperationException();
        }
        return true;
    }

    public void substract(BigDecimal balanceChange) {

        this.accountBalance =
            this.accountBalance.subtract(balanceChange.round(MathContext.DECIMAL32)).round(MathContext.DECIMAL32);
    }

    public void benefit(BigDecimal balanceChange) {
        this.accountBalance =
            this.accountBalance.add(balanceChange.round(MathContext.DECIMAL32)).round(MathContext.DECIMAL32);
    }


    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (!id.equals(account.id)) {
            return false;
        }
        return accountBalance != null ? accountBalance.compareTo(account.accountBalance) == 0 : account.accountBalance == null;
    }

    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (accountBalance != null ? accountBalance.hashCode() : 0);
        return result;
    }

    @Override public String toString() {
        return "Account{" +
               "id=" + id +
               ", accountBalance=" + accountBalance +
               '}';
    }
}
