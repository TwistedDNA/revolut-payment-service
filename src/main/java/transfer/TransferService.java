package transfer;

import account.AccountService;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class TransferService {

    public void executeTransfer(ValidatedTransfer validatedTransfer) {
        AccountService accountService = new AccountService();
        try {

            //TODO startTransaction
            validatedTransfer.source.substract(validatedTransfer.balanceChange);
            validatedTransfer.destination.benefit(validatedTransfer.balanceChange);
            accountService.updateAccount(validatedTransfer.source);
            accountService.updateAccount(validatedTransfer.destination);
            //TODO commitTransaction
        } catch (Exception e) {
            //TODO rollback transaction
            //IN PRODUCTION: inform user / log failure
            e.printStackTrace();
        }


    }

}
