package controllers;

import account.AccountService;
import transfer.TransferService;
import transfer.ValidatedTransfer;

import java.math.BigDecimal;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
//TODO REST endpoint
public class AccountController {

    //@RequestMapping("/transfer//:source/:destination/:decimals/:coins")
    public void transfer(Long sourceAccountId, Long destinationAccountId, BigDecimal balanceChange) {

        TransferService transferService = new TransferService();
        AccountService accountService = new AccountService();

        transferService.executeTransfer(new ValidatedTransfer(accountService.getAccountById(sourceAccountId),
                                                              accountService.getAccountById(destinationAccountId),
                                                              balanceChange));
    }
}
