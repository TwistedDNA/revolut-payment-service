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
        return (this.accountBalance.decimals > balanceChange.decimalsChange) ||
               (this.accountBalance.decimals == balanceChange.decimalsChange
                && this.accountBalance.coins >= balanceChange.coinsChange);
    }

    public void substract(BalanceChange balanceChange) {
        this.accountBalance =
        new AccountBalance(this.accountBalance.decimals - balanceChange.decimalsChange,
                               this.accountBalance.decimals - balanceChange.decimalsChange);
}

    public void benefit(BalanceChange balanceChange) {
        this.accountBalance =
            new AccountBalance(this.accountBalance.decimals + balanceChange.decimalsChange,
                               this.accountBalance.decimals + balanceChange.decimalsChange);
    }
}
