package br.com.alura.clientelo.aplicacao.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("clientelo");

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
