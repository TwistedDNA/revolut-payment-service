package net.twisteddna.account;

import net.twisteddna.entities.Account;
import net.twisteddna.persistence.EntityManagerFactoryProvider;

import java.math.BigDecimal;
import javax.persistence.EntityManager;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class AccountService {

    public Account getAccountById(Long accountId) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Account found = em.find(Account.class, accountId);
        em.getTransaction().commit();
        return found;
    }

    public Account addAccount(BigDecimal balance) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Account accountToAdd = new Account(null, balance);
        em.persist(accountToAdd);
        em.getTransaction().commit();
        return accountToAdd;
    }
}
