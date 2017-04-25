package transfer;

import entities.Account;
import exceptions.TransactionValidationException;


/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class ValidatedTransfer {

    final Account source;
    final Account destination;
    final BalanceChange balanceChange;

    public ValidatedTransfer(Account source, Account destination, BalanceChange balanceChange) throws
                                                                                               TransactionValidationException {
        this.source = source;
        this.destination = destination;
        this.balanceChange = balanceChange;
        validate();
    }

    private void validate() {
        if (source == null) {
            throw new TransactionValidationException("Transfer source account cannot be found!");
        }

        if (destination == null) {
            throw new TransactionValidationException("Transfer destination account cannot be found!");
        }

        if(balanceChange == null){
            throw new TransactionValidationException("Transfer balance change cannot be empty!");
        }

        if (balanceChange.decimalsChange < 0 || balanceChange.coinsChange < 0) {
            throw new TransactionValidationException("Transfer balance change cannot be negetive!");
        }

        if (!source.isEnoughForTransaction(balanceChange)) {
            throw new TransactionValidationException(
                "Transfer source account has insufficient balance for transaction!");
        }
    }
}
