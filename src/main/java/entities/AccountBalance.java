package entities;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class AccountBalance {

    final Long decimals;
    final Long coins;

    public AccountBalance(Long decimals, Long coins) {
        this.decimals = decimals;
        this.coins = coins;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountBalance that = (AccountBalance) o;

        if (!decimals.equals(that.decimals)) {
            return false;
        }
        return coins.equals(that.coins);
    }

    @Override public int hashCode() {
        int result = decimals.hashCode();
        result = 31 * result + coins.hashCode();
        return result;
    }
}
