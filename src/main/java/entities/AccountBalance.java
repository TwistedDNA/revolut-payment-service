package entities;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class AccountBalance {

    final Long decimals;
    final Long coins;

    AccountBalance(Long decimals, Long coins) {
        this.decimals = decimals;
        this.coins = coins;
    }
}
