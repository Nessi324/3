package de.bht.fpa.mail.gruppe6.controller;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.ApplicationLogic;
import de.bht.fpa.mail.gruppe6.model.applicationLogic.ApplicationLogicIF;
import de.bht.fpa.mail.gruppe6.model.data.Account;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Nessi
 */
public class AccountWindowController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField host;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button cancelacc;
    @FXML
    private Button saveacc;
    @FXML
    private Label errortext;

    private AppController app;
    private ApplicationLogicIF logicIF;
    private String modus;

    public AccountWindowController(String modus, AppController aThis) {
        this.modus = modus;
        app = aThis;
        logicIF = new ApplicationLogic();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (modus == null) {
            inItSave();
        }
        if (modus != null) {
            inItEdit();
        }
        cancelacc.setOnAction((value) -> close());
        errortext.setVisible(false);
    }

    private void errorMessage(int x) {
        if (x == 0) {
            errortext.setText("Alle Felder brauchen Daten.");
        }
        if (x == 1) {
            errortext.setText("Account Namens " + name.getText() + " existiert bereits.");
        }
        errortext.setVisible(true);
    }

    private void createAccount() {

        if (logicIF.getAccount(modus) != null) {
            errorMessage(1);
        }
        if (!name.getText().isEmpty() && !host.getText().isEmpty() && !username.getText().isEmpty() && !password.getText().isEmpty()) {
            Account newaccount = new Account(name.getText(), host.getText(), username.getText(), password.getText());
            if (modus == null) {
                logicIF.saveAccount(newaccount);
                close();
            }
            if (modus != null) {
                logicIF.updateAccount(newaccount);
                close();
            }
        }
        else {
            errorMessage(0);
        }
    }

    private void close() {
        Stage window = (Stage) cancelacc.getScene().getWindow();
        window.close();
    }

    private void inItEdit() {
        String nameacc = logicIF.getAccount(modus).getName();
        String hostacc = logicIF.getAccount(modus).getHost();
        String usernameacc = logicIF.getAccount(modus).getUsername();
        String passwordacc = logicIF.getAccount(modus).getPassword();
        name.setText(nameacc);
        name.setEditable(false);
        host.setText(hostacc);
        username.setText(usernameacc);
        password.setText(passwordacc);
        saveacc.setText("update");
        saveacc.setOnAction((value) -> createAccount());
    }

    private void inItSave() {
        saveacc.setText("save");
        saveacc.setOnAction((value) -> createAccount());
    }
}
