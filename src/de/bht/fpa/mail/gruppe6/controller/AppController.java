package de.bht.fpa.mail.gruppe6.controller;

import de.bht.fpa.mail.gruppe6.model.applicationLogic.*;
import java.net.URL;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.stage.*;
import de.bht.fpa.mail.gruppe6.model.data.Component;
import de.bht.fpa.mail.gruppe6.model.data.Email;
import de.bht.fpa.mail.gruppe6.model.data.Folder;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AppController implements Initializable {

    @FXML
    private TreeView<Component> directoryTree;
    @FXML
    private Menu file;
    @FXML
    private TableView<Email> tableview;
    @FXML
    private TableColumn<Email, String> importance;
    @FXML
    private TableColumn<Email, String> received;
    @FXML
    private TableColumn<Email, String> read;
    @FXML
    private TableColumn<Email, String> sender;
    @FXML
    private TableColumn<Email, String> recipients;
    @FXML
    private TableColumn<Email, String> subject;
    @FXML
    private TextField searchField;
    @FXML
    private Label numberOfMails;
    @FXML
    private TextArea textarea;
    @FXML
    private TextFlow textflow;
    @FXML
    private Menu openacc;
    @FXML
    private Menu account;
    @FXML
    private Menu editacc;

    private TreeItem<Component> rootNode;
    private static ArrayList<String> historyData = new ArrayList<String>();
    private static final TreeItem<String> loading = new TreeItem<String>();
    private final Image open = new Image(getClass().getResourceAsStream("/de/bht/fpa/mail/gruppe6/pic/open.png"));
    private final Image close = new Image(getClass().getResourceAsStream("/de/bht/fpa/mail/gruppe6/pic/closed.png"));
    public static ObservableList<Email> tableinfo;
    public ApplicationLogicIF appIF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appIF = new ApplicationLogic();
        numberOfMails.setText("0");
        configureTree();
        searchField.textProperty().addListener((e) -> filterList());
        inItMenue();
        inItTable();
    }

    private void inItMenue() {
        for (String acc : appIF.getAllAccounts()) {
            openacc.getItems().add(new MenuItem(acc));
            editacc.getItems().add(new MenuItem(acc));
        }
        configureMenue(file, (e) -> handleAll(e));
        configureMenue(account, (e) -> handleAll(e));
        openacc.getItems().get(0).setOnAction((value) -> accountAction(openacc.getItems().get(0).getText()));
        openacc.getItems().get(1).setOnAction((value) -> accountAction(openacc.getItems().get(1).getText()));
        openacc.getItems().get(2).setOnAction((value) -> accountAction(openacc.getItems().get(2).getText()));
        editacc.getItems().get(0).setOnAction((value) -> openAccountWindow(editacc.getItems().get(0).getText()));
        editacc.getItems().get(1).setOnAction((value) -> openAccountWindow(editacc.getItems().get(1).getText()));
        editacc.getItems().get(2).setOnAction((value) -> openAccountWindow(editacc.getItems().get(2).getText()));
    }

    public void configureMenue(Menu menu, EventHandler<ActionEvent> handler) {
        for (MenuItem it : menu.getItems()) {
            if (!(it instanceof Menu)) {
                it.setOnAction(handler);
            }
        }
    }

    public void handleAll(ActionEvent e) {
        MenuItem it = (MenuItem) e.getSource();
        String modus = null;
        if (it instanceof MenuItem) {
            switch (it.getText()) {
                case "Open":
                    selectDirectory();
                    break;
                case "History":
                    showHistory();
                    break;
                case "Save":
                    saveEmailDirectoryChooser();
                    break;
                case "New Account":
                    openAccountWindow(modus);
                    break;
            }
        }

    }

    /**
     * saveEmailDirectoryChooser öffnet ein Fenster mit Verzeichnissen, damit
     * wir aussuchen können wo wir die Emails speichern wollen. Und speichert
     * dann diese mit der App.
     */
    private void saveEmailDirectoryChooser() {
        Stage stage = new Stage();
        stage.setTitle("Open New Directory");
        DirectoryChooser fs = new DirectoryChooser();
        File file = fs.showDialog(stage);
        appIF.saveEmails(file);
    }

    /**
     * die Tabelle wird mit Emails gefüllt, das treeitem zeigt uns den Ordner
     * woraus wir die Emails entnehmen.
     */
    private void fillTableWithEmails(TreeItem<Component> treeitem) {
        if (treeitem != null) {
            tableview.getItems().clear();
            Folder f = (Folder) treeitem.getValue();
            appIF.loadEmails(f);
            for (Email x : f.getEmails()) {
                if (x != null && f.getEmails().size() > 0) {
                    tableinfo.add(x);
                }
            }
            tableview.setItems(tableinfo);
            numberOfMails.setText(tableinfo.size() + "");
            directoryTree.refresh();
        }
    }

    /**
     * filterList nimmt die Eingabe aus der Suche auf und nutzt die Methode
     * search aus der App um eine aktuallisierte Liste zu erstellen. Die
     * Ergebnisse werden genutzt,um die Items in der tableview neu zu setzen.
     */
    private void filterList() {
        String pattern = searchField.getText();
        ObservableList<Email> liste = FXCollections.observableArrayList(appIF.search(pattern));
        tableview.setItems(liste);
        //gibt auch bei der Suche regelmäßig die Anzahl der Emails an
        numberOfMails.setText(tableview.getItems().size() + "");
    }

    /**
     * Diese Methode zeigt die Emails des selektierten Ordners an, falls die
     * Emails gleich sind oder null tut die Methode nichts.
     */
    private void showSelectedEmail(Email oldValue, Email newValue) {
        if (newValue == null) {
            textflow.getChildren().clear();
            textarea.clear();
        }
        if (oldValue != newValue && newValue != null) {
            //textflow ist ein simplerer Weg als mehrere Labels zu setzen
            textflow.getChildren().clear();
            Text text = new Text(
                    newValue.getSender() + "\n"
                    + newValue.getSubject() + "\n"
                    + newValue.getReceived() + "\n"
                    + newValue.getReceiver()
            );
            text.setFont(Font.font("System", FontWeight.NORMAL, 12));
            textflow.getChildren().add(text);
            textarea.clear();
            String text2 = (newValue.getText());
            textarea.appendText(text2);
        }
    }

    public void handleExpand(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        BooleanProperty bb = (BooleanProperty) observable;
        TreeItem<Component> t = (TreeItem<Component>) bb.getBean();
        if (newValue) {
            ImageView image = new ImageView(open);
            t.setGraphic(image);
            expandNode(t);
        }
        else if (!newValue) {
            ImageView image = new ImageView(close);
            t.setGraphic(image);
        }
    }

    public void configureTree() {
        Component component = appIF.getTopFolder();
        rootNode = new TreeItem<Component>(component);
        ImageView image = new ImageView(open);
        rootNode.setGraphic(image);
        rootNode.setExpanded(true);
        rootNode.getValue().getComponents().forEach((Component c) -> rootNode.getChildren().add(buildTreeNode(c)));
        directoryTree.setRoot(rootNode);
        directoryTree.getSelectionModel().selectedItemProperty().addListener((obs, old_val, new_val) -> fillTableWithEmails(new_val));
    }

    public TreeItem<Component> buildTreeNode(Component c) {
        TreeItem items = new TreeItem<Component>(c);
        ImageView image = new ImageView(close);
        items.setGraphic(image);
        if (c.isExpandable()) {
            items.expandedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                    -> handleExpand(observable, oldValue, newValue));
            items.getChildren().add(loading);
        }
        else if (!c.isExpandable()) {
            image = new ImageView(open);
            items.setGraphic(image);

        }
        return items;
    }

    public void selectDirectory() {
        DirectoryChooser fs = new DirectoryChooser();
        File s = fs.showDialog(null);
        if (s != null) {
            appIF.changeDirectory(s);
            String y = s.getAbsolutePath();
            addHistory(y);
            configureTree();
        }
    }

    /**
     * Diese Methode öffnet ein neues Fenster um die Funktionen der History zu
     * benutzen.
     *
     */
    public void showHistory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/bht/fpa/mail/gruppe6/view/HistoryWindow.fxml"));
            loader.setController(new HistoryController(this));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 480, 373));
            stage.setTitle("History");
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addHistory(String txt) {
        historyData.add(txt);
    }

    public ArrayList<String> getHistory() {
        return historyData;
    }

    /**
     * historyAction nimmt einen String Wert an und packt ihn als File Element
     * in die Methode configureTree um das Verzeichnis neu zu laden.
     */
    public void historyAction(String x) {
        File file = new File(x);
        appIF.changeDirectory(file);
        configureTree();
    }

    public void accountAction(String name) {
        appIF.openAccount(name);
        configureTree();
    }

    /**
     * compare vergleicht 2 Strings, indem er sie in Date Objekte umwandelt und
     * dann einen Wert zurückgibt den wir zur Sortierung der EMail Tabelle
     * benutzen.
     */
    public int compare(String t1, String t2) {
        Date date1 = null;
        Date date2 = null;
        try {
            if (t1 != null && t2 != null) {
                date1 = Email.FORMAT.parse(t1);
                date2 = Email.FORMAT.parse(t2);
            }
        } catch (ParseException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date1.compareTo(date2);
    }

    public void inItTable() {
        tableinfo = FXCollections.observableArrayList();
        importance.setCellValueFactory(new PropertyValueFactory<>("importance"));
        received.setCellValueFactory(new PropertyValueFactory<>("received"));
        read.setCellValueFactory(new PropertyValueFactory<>("read"));
        sender.setCellValueFactory(new PropertyValueFactory<>("sender"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        recipients.setCellValueFactory(new PropertyValueFactory<>("receiverTo"));
        received.setComparator((String date1, String date2) -> compare(date1, date2));
        tableview.getSortOrder().add(received);
        tableview.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> showSelectedEmail(oldValue, newValue));
    }

    public void expandNode(TreeItem<Component> node) {
        //wenn der Dummy Loading nicht drinne ist kann man es auch nicht expanden
        if (!node.getChildren().get(0).equals(loading)) {
        }
        //der Dummy ist drinne wir können ihn also entfernen und die Components laden
        else {
            node.getChildren().remove(loading);
            Folder folder = (Folder) node.getValue();
            appIF.loadContent(folder);
            folder.getComponents().forEach((Component c) -> node.getChildren().add(buildTreeNode(c)));
        }
    }

    public void openAccountWindow(String modus) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/de/bht/fpa/mail/gruppe6/view/AccountWindow.fxml"));
            loader.setController(new AccountWindowController(modus, this));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
