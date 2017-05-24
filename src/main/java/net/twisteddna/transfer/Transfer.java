package net.twisteddna.transfer;

import java.math.BigDecimal;

/**
 * Created by Maksym_Mazur on 5/24/2017.
 */
public class Transfer {

    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal balanceChange;

    public Transfer(Long sourceAccountId, Long destinationAccountId, BigDecimal balanceChange) {
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.balanceChange = balanceChange;
    }


    public Transfer() {
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public BigDecimal getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(BigDecimal balanceChange) {
        this.balanceChange = balanceChange;
    }
}
