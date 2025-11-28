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

/**
 * FXML Controller class
 *
 * @author POS
 */
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VistaArticulosController implements Initializable, DatosCompartidos {

    private List<Departamento> departamentos;//variable de datoscompartidos
    private List<Familia> familias;
    private List<Rubro> rubros;
    private List<Marca> marcas;
    @FXML
    private TableView<Articulo> TablaArticulos;
    @FXML
    private TableColumn<Articulo, String> ColumDescripcion;
    @FXML
    private TableColumn<Articulo, Number> columCodigo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtCodMarca;
    @FXML
    private TextField  txtCodigo;
    @FXML
    private TextField  txtCodigoBarra;
    @FXML
    private TextField  txtPrecioActual;
    @FXML
    private TextField  txtMargen;
    @FXML
    private TextField  txtPrecioCosto;
    @FXML
    private TextField  txtStock;
    @FXML
    private TextField  txtRubro;
    @FXML
    private TextField  txtFamilia;
    @FXML
    private TextField  txtDepartamento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Política de ajuste automático
        TablaArticulos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        // Proporciones deseadas (ejemplo: 50% / 50%)
        double[] proportions = {0.6, 0.4};
        // Listener para ajustar cuando cambie el ancho del TableView
        TablaArticulos.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double totalWidth = newWidth.doubleValue();
            ColumDescripcion.setPrefWidth(totalWidth * proportions[0]);
            columCodigo.setPrefWidth(totalWidth * proportions[1]);
        });
        //cargar datos en tabla
        ColumDescripcion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        columCodigo.setCellValueFactory(data -> new SimpleLongProperty(data.getValue().getCodigoBarra()));        
        cargarTabla();
        //cargar detalles de articulo
        TablaArticulos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarDetalles(newSel);
            }
        });

    }

    public void cargarTabla() {
        ArticuloDAO dao = new ArticuloDAOImpl();
        List<Articulo> lista = dao.listarTodos();

        ObservableList<Articulo> obs = FXCollections.observableArrayList(lista);
        TablaArticulos.setItems(obs);
    }

    public void cargarDetalles(Articulo a) {
        txtDescripcion.setText(String.valueOf(a.getDescripcion()));
        txtMarca.setText(String.valueOf(marcas.get(a.getMarca()-1).getDescripcion()));
        txtCodMarca.setText(String.valueOf(marcas.get(a.getMarca()-1).getCodMarca()));
        txtCodigo.setText(String.valueOf(a.getCodigo()));        
        txtCodigoBarra.setText(String.valueOf(a.getCodigoBarra()));
        txtDepartamento.setText(String.valueOf( departamentos.get(a.getCodDepartamento()-1).getDescripcion()));
        txtFamilia.setText(String.valueOf(familias.get(a.getCodFamilia()-1).getDescripcion()));
        txtMargen.setText(String.valueOf(a.getMargen()));
        txtPrecioActual.setText(String.valueOf(a.getPrecioVenta()));
        txtPrecioCosto.setText(String.valueOf(a.getPrecioCosto()));
        txtRubro.setText(String.valueOf(rubros.get(a.getCodRubro()-1).getDescripcion()));
        txtStock.setText(String.valueOf(a.getStock()));
        
    }

    @Override
    public void setDatos(List<Departamento> departamentos, List<Familia> familias, List<Rubro> rubros, List<Marca> marcas) {
        this.departamentos = departamentos;
        this.familias = familias;
        this.rubros = rubros;
        this.marcas = marcas;
    }

}
