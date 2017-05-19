package net.twisteddna.controllers;

import net.twisteddna.account.AccountService;
import net.twisteddna.entities.Account;
import net.twisteddna.transfer.TransferService;
import net.twisteddna.transfer.ValidatedTransfer;

import java.math.BigDecimal;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
@Path("/")
public class AccountController {

    @POST
    @Path("/{sourceAccountId}/{destinationAccountId}/{balanceChange}")
    public Response transfer(@PathParam("sourceAccountId") Long sourceAccountId,
                             @PathParam("destinationAccountId") Long destinationAccountId,
                             @PathParam("balanceChange") BigDecimal balanceChange) {

        TransferService transferService = new TransferService();
        AccountService accountService = new AccountService();

        transferService.executeTransfer(new ValidatedTransfer(accountService.getAccountById(sourceAccountId),
                                                              accountService.getAccountById(destinationAccountId),
                                                              balanceChange));
        return Response.status(200).entity("OK").build();
    }

    @POST
    @Path("/accounts/add/with-balance/{balance}")
    public String addAccount(@PathParam("balance") BigDecimal balance){
        AccountService accountService = new AccountService();
        Account created = accountService.addAccount(balance);
        return created.toString();
    }

    @GET
    public String helloworld() {
        return "Hello World!";
    }
}
