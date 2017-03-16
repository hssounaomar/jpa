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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ideal-Info
 */
public class ViewController implements Initializable {

    @FXML
    private TextField text_name;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_remove;
    @FXML
    private ListView<Personne> row_personnes;
    private ObservableList<Personne> personnes_list=FXCollections.observableArrayList();
    private final PersonneDAO dao=new PersonneDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        row_personnes.setItems(personnes_list);
        personnes_list.addAll(dao.getList());
       //add Personne
    btn_add.setOnAction(e->{
       String str= text_name.getText().toString();
      Personne per= new Personne();
      per.setEmail(str);
      per.setPassword(str);
       dao.addPersonne(per);
    });
    //Remove Personne
    btn_remove.setOnAction(e->{
     String  str=  text_name.getText().toString();
     Integer id= Integer.parseInt(str);
            try {
                dao.removePersonne(id);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
}
}
