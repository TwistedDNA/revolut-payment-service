package transfer;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class BalanceChange {
    public final Long decimalsChange;
    public final Long coinsChange;

    public BalanceChange(Long decimalsChange, Long coinsChange) {
        this.decimalsChange = decimalsChange;
        this.coinsChange = coinsChange;
    }
}
