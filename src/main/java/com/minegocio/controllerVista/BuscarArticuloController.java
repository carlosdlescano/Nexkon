/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.model.Articulo;
import com.minegocio.util.util;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miNegocio
 */
public class BuscarArticuloController implements Initializable {

    @FXML
    private TableView<Articulo> tablaBusqueda;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TableColumn<Articulo, String> colDescripcion;
    @FXML
    private TableColumn<Articulo, Integer> colStock;
    @FXML
    private TableColumn<Articulo, Double> colPrecio;
    @FXML
    private TableColumn<Articulo, Integer> colCodigo;

    private VistaPosController posController; //referencia al controlador de la vista pos

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Cargar columnas
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));

        // Listener de búsqueda en vivo
        txtDescripcion.textProperty().addListener((obs, oldValue, newValue) -> {
            buscarDescripcion(newValue);
        });
    }

    public void setPosController(VistaPosController posController) {
        this.posController = posController;
    }

    public void manejarClicTabla() {
        Articulo seleccionado = tablaBusqueda.getSelectionModel().getSelectedItem();
        if (seleccionado != null && posController != null) {
            // Agregar el artículo seleccionado a la venta
            posController.agregarFilaVenta(seleccionado);

            // Cerrar la ventana de búsqueda
            ((Stage) tablaBusqueda.getScene().getWindow()).close();
        }
    }

    public void buscarDescripcion(String texto) {
        ArticuloDAO dao = new ArticuloDAOImpl();
        ArrayList<Articulo> lista = dao.buscarDescripcion(texto);

        tablaBusqueda.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void buscarArticulo(KeyEvent event) {
        // Obtener el texto ingresado en el TextField
        String textoBusqueda = txtDescripcion.getText();
        try{
        // Llamar al método para hacer la búsqueda en la base de datos o en memoria
        buscarDescripcion(textoBusqueda);
        }catch(Exception ex){
            util.mostrarAlerta("", "Error al buscar", Alert.AlertType.INFORMATION, false);
        }
    }

}
