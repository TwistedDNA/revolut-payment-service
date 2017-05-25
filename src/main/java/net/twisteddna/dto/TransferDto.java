package net.twisteddna.dto;

import java.math.BigDecimal;

/**
 * Created by Maksym_Mazur on 5/24/2017.
 */
public class TransferDto {

    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal balanceChange;

    public TransferDto(Long sourceAccountId, Long destinationAccountId, BigDecimal balanceChange) {
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.balanceChange = balanceChange;
    }


    public TransferDto() {
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
