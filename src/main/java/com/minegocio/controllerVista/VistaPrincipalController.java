/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
/**
 * FXML Controller class
 *
 * @author POS
 */
public class VistaPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private StackPane ContenedorCentral;

    private void cargarVista(String rutaFXML) {
        try {
            AnchorPane nuevaVista = FXMLLoader.load(getClass().getResource("/Vistas/" + rutaFXML));
            ContenedorCentral.getChildren().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@FXML
    private void abrirVistaCliente() {
        cargarVista("VistaCliente.fxml");
    }

    @FXML
    private void abrirVistaProveedor() {
        cargarVista("VistaProveedor.fxml");
    }
*/
    @FXML
    private void abrirVistaArticulo() {
        cargarVista("VistaArticulos.fxml");
    }

    @FXML
    private void abrirVistaVenta() {
        cargarVista("VistaPos.fxml");
    }
}
    

