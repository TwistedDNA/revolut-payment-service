package entities;

import exceptions.IncompleteParameterObjectException;
import exceptions.NotEnoughBalanceForOperationException;
import transfer.BalanceChange;

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

    private AccountBalance accountBalance;

    public Account(Long id, AccountBalance accountBalance) {
        this.id = id;
        this.accountBalance = accountBalance;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountBalance getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(AccountBalance accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean isEnoughForTransaction(BalanceChange balanceChange) throws NotEnoughBalanceForOperationException {
        balanceChangeValidation(balanceChange);

        if ((this.accountBalance.decimals < balanceChange.decimalsChange) ||
            (this.accountBalance.decimals.equals(balanceChange.decimalsChange)
             && this.accountBalance.coins < balanceChange.coinsChange)) {
            throw new NotEnoughBalanceForOperationException();
        }

        return true;
    }

    public void substract(BalanceChange balanceChange) {
        balanceChangeValidation(balanceChange);
        this.accountBalance =
            new AccountBalance(this.accountBalance.decimals - balanceChange.decimalsChange,
                               this.accountBalance.coins - balanceChange.coinsChange);
    }

    public void benefit(BalanceChange balanceChange) {
        balanceChangeValidation(balanceChange);
        this.accountBalance =
            new AccountBalance(this.accountBalance.decimals + balanceChange.decimalsChange,
                               this.accountBalance.coins + balanceChange.coinsChange);
    }

    private void balanceChangeValidation(BalanceChange balanceChange) throws IncompleteParameterObjectException {
        if (balanceChange == null || balanceChange.coinsChange == null || balanceChange.decimalsChange == null) {
            throw new IncompleteParameterObjectException("BalanceChange object is not valid!");
        }
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
        return accountBalance != null ? accountBalance.equals(account.accountBalance) : account.accountBalance == null;
    }

    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (accountBalance != null ? accountBalance.hashCode() : 0);
        return result;
    }
}
