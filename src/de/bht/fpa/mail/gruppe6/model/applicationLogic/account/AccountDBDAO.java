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

/**
 *
 * @author Nessi
 */
public class AccountDBDAO implements AccountDAOIF {

    EntityManagerFactory emf;
    public AccountDBDAO() {
    TestDBDataProvider.createAccounts();
    }

    public List<Account> getAllAccounts() {
      return null;
    }

    public Account saveAccount(Account acc) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(acc);
        trans.commit();
        em.close();
        return acc;
    }
        
    public void removeAccount(){}
    

    public boolean updateAccount(Account acc) {
        return false;
    }

    public void remove(String name, List<Account> list) {

    }
}
