/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bht.fpa.mail.gruppe6.model.applicationLogic;

import de.bht.fpa.mail.gruppe6.model.data.Account;
import java.util.List;

/**
 *
 * @author Nessi
 */
public interface AccountManagerIF {

    Account getAccount(String name);

    List<Account> getAllAccounts();

     void saveAccount(Account acc);

     boolean updateAccount(Account account);
}
