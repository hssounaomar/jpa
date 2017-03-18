/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Ideal-Info
 */
public class User implements Initializable{
@FXML
private Label text_user;
    
     public void getUser(String str) {
         text_user.setText(str);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
