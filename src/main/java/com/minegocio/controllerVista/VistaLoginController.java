/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.util.util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miNegocio
 */
public class VistaLoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;

    private String user;
    private String pass;
    private Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = "admin";
        pass = "1234";
    }

    public void ingresar() {
        if (user.equals(txtUser.getText()) && pass.equals(txtPass.getText())) {
            stage = (Stage) txtUser.getScene().getWindow();
            stage.close();

            // abrir pantalla principal
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/VistaPrincipal.fxml"));
                Parent root = loader.load();
                Stage mainStage = new Stage();
                Scene scene = new Scene(root, 640, 480);
                scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
                mainStage.setTitle("NexKon");
                mainStage.setScene(scene);
                mainStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            util.mostrarAlerta("", "Usuario o Contraseña incorrectos", Alert.AlertType.WARNING, false);
        }

    }
    
    public void cerrar(){
        stage.close();
    }
}


