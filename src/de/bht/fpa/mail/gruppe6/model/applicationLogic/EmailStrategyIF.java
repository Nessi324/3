package de.bht.fpa.mail.gruppe6.model.applicationLogic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import de.bht.fpa.mail.gruppe6.model.data.Folder;

/**
 *
 * @author Nessi
 */
public interface EmailStrategyIF {

    public void loadEmails(Folder f);
}
