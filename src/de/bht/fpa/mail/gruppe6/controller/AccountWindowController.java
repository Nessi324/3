package de.bht.fpa.mail.gruppe6.controller;

import de.bht.fpa.mail.gruppe6.model.data.Account;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Account account;

    public AccountWindowController(Account account, AppController aThis) {
        this.account = account;
        app = aThis;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cancelacc.setOnAction((value) -> close());
        saveacc.setOnAction((value) -> createAccount());
        errortext.setVisible(false);
        if (account == null) {
            inItSave();
        }
        if (account != null) {
            inItEdit();
        }
    }

    private void createAccount() {
        if (!name.getText().isEmpty() && !host.getText().isEmpty() && !username.getText().isEmpty() && !password.getText().isEmpty()) {
            if (account != null) {
                account.setHost(host.getText());
                account.setPassword(password.getText());
                account.setUsername(username.getText());
                app.editAccount(account);
            }
            else if (account == null) {
                Account newaccount = new Account(name.getText(), host.getText(), username.getText(), password.getText());
                app.newAccount(newaccount);

            }
            close();
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
        String nameacc = account.getName();
        String hostacc = account.getHost();
        String usernameacc = account.getUsername();
        String passwordacc = account.getPassword();
        name.setText(nameacc);
        name.setEditable(false);
        host.setText(hostacc);
        username.setText(usernameacc);
        password.setText(passwordacc);
        saveacc.setText("update");
    }

    private void inItSave() {
        saveacc.setText("save");
    }

    private void errorMessage(int x) {
        errortext.setText("Alle Felder brauchen Daten.");
        errortext.setVisible(true);
    }
}
