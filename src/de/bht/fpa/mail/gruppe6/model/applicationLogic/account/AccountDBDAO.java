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

    // EntityManagerFactory emf;
    public AccountDBDAO() {
        // emf = Persistence.createEntityManagerFactory("account");
        TestDBDataProvider.createAccounts();
    }

    @Override
    public List<Account> getAllAccounts() {
//        EntityManager em = emf.createEntityManager(); 
//        Query query = em.createQuery("SELECT * FROM accs");
//        List<Account> List = query.getResultList();
//        return List;
        return null;
    }

    @Override
    public Account saveAccount(Account acc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateAccount(Account acc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
