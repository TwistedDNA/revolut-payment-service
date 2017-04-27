package entities;

import transfer.BalanceChange;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */

//TODO entity wannaBe
public class Account {

    private Long id;
    private AccountBalance accountBalance;

    public Account(Long id, AccountBalance accountBalance) {
        this.id = id;
        this.accountBalance = accountBalance;
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

    public boolean isEnoughForTransaction(BalanceChange balanceChange) {
        balanceChangeValidation(balanceChange);
        return (this.accountBalance.decimals > balanceChange.decimalsChange) ||
               (this.accountBalance.decimals == balanceChange.decimalsChange
                && this.accountBalance.coins >= balanceChange.coinsChange);
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

    private void balanceChangeValidation(BalanceChange balanceChange) throws IllegalArgumentException {
        if (balanceChange == null || balanceChange.coinsChange == null || balanceChange.decimalsChange == null) {
            throw new IllegalArgumentException("BalanceChange object is not valid!");
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
