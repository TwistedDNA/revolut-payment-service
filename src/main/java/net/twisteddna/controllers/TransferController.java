package net.twisteddna.controllers;

import net.twisteddna.account.AccountService;
import net.twisteddna.dto.TransferDto;
import net.twisteddna.transfer.TransferService;
import net.twisteddna.transfer.ValidatedTransfer;

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
    public Response transfer(TransferDto transfer) {

        TransferService transferService = new TransferService();
        AccountService accountService = new AccountService();

        transferService
            .executeTransfer(new ValidatedTransfer(accountService.getAccountById(transfer.getSourceAccountId()),
                                                   accountService.getAccountById(transfer.getDestinationAccountId()),
                                                   transfer.getBalanceChange()));

        return Response.status(201).entity("OK").build();
    }
}
