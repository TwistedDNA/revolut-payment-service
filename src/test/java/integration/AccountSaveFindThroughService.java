package integration;

import static org.junit.Assert.assertEquals;

import account.AccountService;
import entities.Account;
import entities.AccountBalance;
import org.junit.Test;
import persistence.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;

/**
 * Created by Maksym_Mazur on 4/27/2017.
 */
public class AccountSaveFindThroughService {

    @Test
    public void canSaveAndFindAccountThroughAccountService() {
        Account toSaveToDb = new Account(null, new AccountBalance(new Long(6), new Long(6)));
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();
        em.persist(toSaveToDb);
        em.getTransaction().commit();

        Account received = new AccountService().getAccountById(toSaveToDb.getId());

        assertEquals(toSaveToDb, received);
    }

}
