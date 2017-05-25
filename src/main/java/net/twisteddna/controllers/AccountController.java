package net.twisteddna.controllers;

import net.twisteddna.account.AccountService;
import net.twisteddna.dto.BalanceDto;
import net.twisteddna.entities.Account;

import java.math.BigDecimal;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Crafted by TwistedDNA on 4/25/2017.
 */
@Path("/accounts")
public class AccountController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAccount(BalanceDto balanceDto) {
        AccountService accountService = new AccountService();
        Account created = accountService.addAccount(balanceDto.getBalance());

        return Response.status(201).entity(created.getId()).build();
    }


    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account accountDetails(@PathParam("accountId") Long accountId) {
        AccountService accountService = new AccountService();
        return accountService.getAccountById(accountId);
    }

    @GET
    @Path("/{accountId}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal getBalance(@PathParam("accountId") Long accountId) {
        AccountService accountService = new AccountService();
        return accountService.getAccountById(accountId).getAccountBalance();
    }
}
