/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bht.fpa.mail.gruppe6.model.applicationLogic.account;

import de.bht.fpa.mail.gruppe6.model.data.Account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nessi
 */
public class AccountDBDAO implements AccountDAOIF {

    EntityManagerFactory emf;

    public AccountDBDAO() {
        emf = Persistence.createEntityManagerFactory("account");
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> List = null;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select account from Account account");
        List = query.getResultList();
        System.out.println(List);
        return List;
    }

    @Override
    public Account saveAccount(Account acc) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(acc);
        trans.commit();
        em.close();
        return acc;
    }

    @Override
    public boolean updateAccount(Account acc) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        acc = em.merge(acc);
        trans.commit();
        em.close();
        return true;
    }
}
