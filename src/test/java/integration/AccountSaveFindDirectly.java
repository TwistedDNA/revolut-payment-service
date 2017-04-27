package integration;

import static org.junit.Assert.assertEquals;

import entities.Account;
import org.junit.Test;
import persistence.EntityManagerFactoryProvider;

import java.math.BigDecimal;
import javax.persistence.EntityManager;

/**
 * Created by Maksym_Mazur on 4/27/2017.
 */
public class AccountSaveFindDirectly {


    @Test
    public void canPersistAccountAndReadItBack() {
        EntityManager entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Account toSaveToDb = new Account(null, new BigDecimal(7.7));

        entityManager.persist(toSaveToDb);

        entityManager.getTransaction().commit();
        entityManager.close();

        //-------

        entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Account receivedFromDb = entityManager.find(Account.class, toSaveToDb.getId());

        assertEquals(toSaveToDb, receivedFromDb);
    }

}
