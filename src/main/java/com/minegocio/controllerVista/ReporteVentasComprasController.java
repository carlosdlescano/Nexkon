/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.DAO.DetalleVentaDAO;
import com.minegocio.DAO.VentaDAO;
import com.minegocio.DaoImpl.DetalleVentaDAOImpl;
import com.minegocio.DaoImpl.VentaDAOImpl;
import com.minegocio.model.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author miNegocio
 */
public class ReporteVentasComprasController implements Initializable {

    @FXML
    private DatePicker dataInicio;
    @FXML
    private DatePicker dataFin;
    @FXML
    private CheckBox checkVentas;
    @FXML
    private CheckBox checkCompras;
    @FXML
    private TableView<Venta> tablaPrincipal;
    @FXML
    private TableColumn<Venta, Integer> colNro;
    @FXML
    private TableColumn<Venta, Timestamp> colFecha;
    @FXML
    private TableColumn<Venta, Double> colTotal;
    @FXML
    private TableView<DetalleVenta> tablaDetalles;
    @FXML
    private TableColumn<DetalleVenta, String> colDescripcion;
    @FXML
    private TableColumn<DetalleVenta, Integer> colCantidad;
    @FXML
    private TableColumn<DetalleVenta, Double> colPrecioUni;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
// Configuración tabla principal
        colNro.setCellValueFactory(new PropertyValueFactory<>("idventa"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalVenta"));

        // Configuración tabla detalle
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioUni.setCellValueFactory(new PropertyValueFactory<>("precio"));
        // Listener: cuando selecciono una venta, cargo el detalle
        tablaPrincipal.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarDetalleVenta(newSel.getIdventa());
            }
        });
    }

    public void cargarVentas() {
        
        if (checkVentas.isSelected() && checkCompras.isSelected()) {
            // No se pueden seleccionar ambos
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione solo Ventas o Compras.");
            alert.showAndWait();
            return;
        }

        // Convertir fechas de DatePicker a Timestamp
        Timestamp inicio = dataInicio.getValue() != null ? Timestamp.valueOf(dataInicio.getValue().atStartOfDay()) : null;
        Timestamp fin = dataFin.getValue() != null ? Timestamp.valueOf(dataFin.getValue().atTime(23, 59, 59)) : null;

        VentaDAO dao = new VentaDAOImpl();
        List<Venta> lista = dao.buscarVenta(inicio, fin, null, null); // cliente y medioPago null
        
        ObservableList<Venta> obs = FXCollections.observableArrayList(lista);
        tablaPrincipal.setItems(obs);
    }
    

    public void cargarDetalleVenta(int idVenta) {
        DetalleVentaDAO dao = new DetalleVentaDAOImpl();
        List<DetalleVenta> lista = dao.buscarVentaNro(idVenta);

        ObservableList<DetalleVenta> obs = FXCollections.observableArrayList(lista);
        for (DetalleVenta d : lista) {
        System.out.println("Articulo: " + d.getNombreArticulo() + " Cantidad: " + d.getCantidad());}

        tablaDetalles.setItems(obs);
    }

}
