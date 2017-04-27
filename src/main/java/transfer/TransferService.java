package transfer;

import persistence.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;

/**
 * Created by Maksym_Mazur on 4/25/2017.
 */
public class TransferService {

    public void executeTransfer(ValidatedTransfer validatedTransfer) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        validatedTransfer.source.substract(validatedTransfer.balanceChange);
        validatedTransfer.destination.benefit(validatedTransfer.balanceChange);

        em.persist(validatedTransfer.source);
        em.persist(validatedTransfer.destination);

        em.getTransaction().commit();
    }

}
