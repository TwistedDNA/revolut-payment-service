package net.twisteddna.dto;

import java.math.BigDecimal;

/**
 * Created by Maksym_Mazur on 5/25/2017.
 */
public class BalanceDto {

    private BigDecimal balance;

    public BalanceDto() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
