/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import dao.PersonneDAO;
import entites.Personne;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.NoResultException;

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
    private TableColumn<Personne, Integer> id;
    @FXML
    private TableColumn<Personne, String> email;
    @FXML
    private TableColumn<Personne, String> password;

    private final PersonneDAO dao = new PersonneDAO();
    private ObservableList<Personne> personnes_list = FXCollections.observableArrayList(dao.getList());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        id.setCellValueFactory(new PropertyValueFactory<Personne, Integer>("Id"));
        email.setCellValueFactory(new PropertyValueFactory<Personne, String>("Email"));
        password.setCellValueFactory(new PropertyValueFactory<Personne, String>("Password"));
        password.setCellFactory(TextFieldTableCell.forTableColumn());
        password.setOnEditCommit((event) -> {
            event.getNewValue();//la nouvelle valeur
            Personne per = event.getRowValue();
            per.setPassword(event.getNewValue());
            try {
                dao.update(per);
            } catch (Exception ex) {
                ex.fillInStackTrace();
            }

        });

        table.setItems(personnes_list);

        table.setEditable(true);

    }

    public void addPersonne(Event e) {

        if ((text_email.getText() != null) && (text_password.getText() != null)) {
            try {
                dao.getPersonneByEmail(text_email.getText());
                 System.err.println("Votre Email ");
            }catch(Exception ex){
                Personne per = new Personne();
                per.setEmail(text_email.getText().toString());
                per.setPassword(text_password.getText().toString());
                dao.addPersonne(per);
                personnes_list.clear();
                personnes_list.addAll(dao.getList());
            }
                
           

        }
    }

    @FXML
    public void connectPersonne(ActionEvent e) throws IOException  {
        try {

            Personne per = dao.getPersonnesByQuery(text_email.getText(), text_password.getText());
            
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(this.getClass().getResource("user.fxml").openStream());
                User user = (User) loader.getController();
                user.getUser(text_email.getText());
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            
              
            

        } 
        catch(NoResultException ex){
           Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("messageBox.fxml").openStream());
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
        }
        catch(IOException ioe){
            
        }

    }
}
