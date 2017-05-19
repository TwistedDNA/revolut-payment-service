package net.twisteddna.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Maksym_Mazur on 4/27/2017.
 */
public class EntityManagerFactoryProvider {

    private static EntityManagerFactory factory = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("revolutPu");
        }
        return factory;
    }
}
