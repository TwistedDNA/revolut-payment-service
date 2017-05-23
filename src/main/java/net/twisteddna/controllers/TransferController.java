package net.twisteddna.controllers;

import net.twisteddna.account.AccountService;
import net.twisteddna.transfer.TransferService;
import net.twisteddna.transfer.ValidatedTransfer;

import java.math.BigDecimal;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Maksym_Mazur on 5/22/2017.
 */
@Path("/transfers")
public class TransferController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transfer(Long sourceAccountId,
                             Long destinationAccountId,
                             BigDecimal balanceChange) {

        TransferService transferService = new TransferService();
        AccountService accountService = new AccountService();

        transferService.executeTransfer(new ValidatedTransfer(accountService.getAccountById(sourceAccountId),
                                                              accountService.getAccountById(destinationAccountId),
                                                              balanceChange));

        return Response.status(201).entity("OK").build();
    }
}
