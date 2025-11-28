/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.DAO.*;
import com.minegocio.DaoImpl.*;
import com.minegocio.model.*;
import com.minegocio.util.DatosCompartidos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author POS
 */

public class VistaPrincipalController implements Initializable {

    private List<Departamento> listaDepartamentos;
    private List<Familia> listaFamilias;
    private List<Rubro> listaRubros;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //carga las listas reutilizadas auxiliares
        DepartamentoDAO dpto = new DepartamentoDAOImpl();
        listaDepartamentos = dpto.ListarTodos();
        FamiliaDAO fam = new FamiliaDAOImpl();
        listaFamilias = fam.ListarTodos();
        RubroDAO rub = new RubroDAOImpl();
        listaRubros = rub.ListarTodos();
        
    }
    @FXML
    private StackPane ContenedorCentral;

    private void cargarVista(String rutaFXML) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/" + rutaFXML));
        AnchorPane nuevaVista = loader.load();

        Object controller = loader.getController();

        // Si el controlador implementa la interfaz, le paso las listas
        if (controller instanceof DatosCompartidos) {
              DatosCompartidos ctrl = (DatosCompartidos) controller;
             ctrl.setDatos(listaDepartamentos,listaFamilias, listaRubros);
         }
            ContenedorCentral.getChildren().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//cargar vista



       
    @FXML
    private void abrirVistaArticulo() {
        cargarVista("VistaArticulos.fxml");
    }

    @FXML
    private void abrirVistaVenta() {
        cargarVista("VistaPos.fxml");
    }

   
}
