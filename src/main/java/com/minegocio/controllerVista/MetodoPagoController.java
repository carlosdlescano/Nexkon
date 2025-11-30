/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.controllerVista.VistaPosController;
import com.minegocio.model.DetalleVenta;
import com.minegocio.util.util;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miNegocio
 */
public class MetodoPagoController implements Initializable {

    private ObservableList<DetalleVenta> listaDetalles;
    private VistaPosController posController;   // referencia al controlador padre de este
    private String metodoPagoSeleccionado; // "Efectivo", "QR", "Tarjeta"

    // Setter para recibir la referencia al POSController
    public void setPosController(VistaPosController posController) {
        this.posController = posController;
    }

    // Setter para recibir la lista de detalles
    public void setListaDetalles(ObservableList<DetalleVenta> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inicializar botones de método de pago, etc.
    }

    @FXML
    private void seleccionarMetodoPago(ActionEvent event) {
        // ejemplo: asignar el método de pago según el botón presionado
        Button btn = (Button) event.getSource();
        metodoPagoSeleccionado = btn.getText();
    }

    @FXML
    private void confirmar(ActionEvent event) {
        try {
            if (listaDetalles.isEmpty() || listaDetalles == null) {
                util.mostrarAlerta("Error", "No hay articulos en la lista", Alert.AlertType.WARNING, false);
                return;
            }
            if (posController == null) {
                util.mostrarAlerta("Error", "No se puede acceder al POS", Alert.AlertType.ERROR, false);
                return;
            }
            if (metodoPagoSeleccionado == null) {
                util.mostrarAlerta("Seleccione medio de pago", "Debe elegir Efectivo o QR o Tajeta antes de continuar", Alert.AlertType.INFORMATION, false);
                return;
            }
        } catch (Exception ex) {
            util.mostrarAlerta("Error inesperado", "Ocurrio un problema al confirmar el pago", Alert.AlertType.ERROR, false);
        }
        //if (listaDetalles != null && posController != null && metodoPagoSeleccionado != null) {

        posController.confirmarVenta(metodoPagoSeleccionado);
        System.out.println("************MEDIOP************" + metodoPagoSeleccionado);
        //}

        // cerrar la ventana
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        // cerrar la ventana sin hacer nada
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
    }
}
