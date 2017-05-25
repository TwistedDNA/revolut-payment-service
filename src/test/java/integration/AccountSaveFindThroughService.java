package integration;

import static org.junit.Assert.assertEquals;

import net.twisteddna.account.AccountService;
import net.twisteddna.entities.Account;
import org.junit.Test;
import net.twisteddna.persistence.EntityManagerFactoryProvider;

import java.math.BigDecimal;
import javax.persistence.EntityManager;

/**
 * Created by Maksym_Mazur on 4/27/2017.
 */
public class AccountSaveFindThroughService {

    @Test
    public void canSaveAndFindAccountThroughAccountService() {
        Account toSaveToDb = new Account(null, BigDecimal.valueOf(6.6));
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();
        em.persist(toSaveToDb);
        em.getTransaction().commit();

        Account received = new AccountService().getAccountById(toSaveToDb.getId());

        assertEquals(toSaveToDb, received);
    }

}
