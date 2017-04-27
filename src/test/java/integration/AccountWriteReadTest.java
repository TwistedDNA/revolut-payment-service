package integration;

import static org.junit.Assert.assertEquals;

import entities.Account;
import entities.AccountBalance;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Maksym_Mazur on 4/27/2017.
 */
public class AccountWriteReadTest {

    private static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("revolutPu");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    @Test
    public void canPersistAccountAndReadItBack() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Account toSaveToDb = new Account(null, new AccountBalance(new Long(7), new Long(7)));

        entityManager.persist(toSaveToDb);

        entityManager.getTransaction().commit();
        entityManager.close();

        //-------

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Account receivedFromDb = entityManager.find(Account.class, toSaveToDb.getId());

        assertEquals(toSaveToDb, receivedFromDb);
    }

}
