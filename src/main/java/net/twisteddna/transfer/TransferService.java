package net.twisteddna.transfer;

import net.twisteddna.persistence.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;

/**
 * Crafted by TwistedDNA on 4/25/2017.
 */
public class TransferService {

    public void executeTransfer(ValidatedTransfer validatedTransfer) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        validatedTransfer.source.substract(validatedTransfer.balanceChange);
        validatedTransfer.destination.benefit(validatedTransfer.balanceChange);

        em.merge(validatedTransfer.source);
        em.merge(validatedTransfer.destination);

        em.getTransaction().commit();
    }

}
