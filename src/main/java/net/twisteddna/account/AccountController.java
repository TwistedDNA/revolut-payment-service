package net.twisteddna.account;

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
 * Created by Maksym_Mazur on 4/25/2017.
 */
@Path("/accounts")
public class AccountController {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAccount(BigDecimal balance) {
        AccountService accountService = new AccountService();
        Account created = accountService.addAccount(balance);

        return Response.status(201).entity(created.getId()).build();
    }

    @GET
    @Path("/{accountId}/balance")
    @Produces(MediaType.APPLICATION_JSON)
    public BigDecimal helloworld(@PathParam("accountId") Long accountId) {
        AccountService accountService = new AccountService();
        BigDecimal balance = accountService.getAccountById(accountId).getAccountBalance();
        return balance;
    }
}
