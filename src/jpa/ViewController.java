/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import dao.PersonneDAO;
import entites.Personne;
import entites.exceptions.NonexistentEntityException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ideal-Info
 */
public class ViewController implements Initializable {

    @FXML
    private TextField text_email;
    @FXML
    private PasswordField text_password;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_remove;
    @FXML
    private TableView<Personne> table;
    @FXML
    private TableColumn<Personne,Integer> id;
    @FXML
    private TableColumn<Personne,String> email;
    @FXML
    private TableColumn<Personne,String> password;
    
    private final PersonneDAO dao=new PersonneDAO();
private ObservableList<Personne> personnes_list=FXCollections.observableArrayList(dao.getList());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
 id.setCellValueFactory(new PropertyValueFactory<Personne,Integer>("Id"));
 email.setCellValueFactory(new PropertyValueFactory<Personne,String>("Email"));
 password.setCellValueFactory(new PropertyValueFactory<Personne,String>("Password"));
 table.setItems(personnes_list);
}
    public void addPersonne(Event e){
       if((text_email.getText()!=null)&&(text_password.getText()!=null)){
           Personne per=new Personne();
           per.setEmail(text_email.getText().toString());
           per.setPassword(text_password.getText().toString());
           dao.addPersonne(per);
           personnes_list.clear();
           personnes_list.addAll(dao.getList());
           table.setItems(personnes_list);
       }
    }
}
