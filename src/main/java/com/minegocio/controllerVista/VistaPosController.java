/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.DAO.VentaDAO;
import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.DaoImpl.VentaDAOImpl;
import com.minegocio.model.Articulo;
import com.minegocio.model.DetalleVenta;
import static com.minegocio.util.util.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author POS
 */
public class VistaPosController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TableView<DetalleVenta> tablaArticulosVenta;
    @FXML
    private TableColumn<DetalleVenta, String> colDescripcion;
    @FXML
    private TableColumn<DetalleVenta, Number> colPrecio;
    @FXML
    private TableColumn<DetalleVenta, Number> colCantidad;
    @FXML
    private TableColumn<DetalleVenta, Number> colSubtotal;
    @FXML
    private ObservableList<DetalleVenta> listaDetalles;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatInteger(txtCantidad);
        txtCantidad.setText("1");

        colDescripcion.setCellValueFactory(data
                -> new SimpleObjectProperty<>(data.getValue().getArticulo().getDescripcion())
        );

        colPrecio.setCellValueFactory(data
                -> new SimpleObjectProperty<>(data.getValue().getPrecio())
        );

        colCantidad.setCellValueFactory(data
                -> new SimpleObjectProperty<>(data.getValue().getCantidad())
        );

        colSubtotal.setCellValueFactory(data
                -> new SimpleObjectProperty<>(data.getValue().getSubtotal())
        );

        listaDetalles = FXCollections.observableArrayList();
        tablaArticulosVenta.setItems(listaDetalles);
    }

    @FXML
    private void agregarArticuloPorCodigo() {
        String texto = txtCodigo.getText().trim();
        System.out.println("BUSCAR " + texto);

        if (texto.isEmpty()) {
            return;
        }

        try {
            long codigoDeBarra = Long.parseLong(texto);
            ArticuloDAO dao = new ArticuloDAOImpl();
            //codigo,marca,descipcion,departamento,rubro,familia,codigodebarra
            Articulo art = dao.buscarArticulo(0, 0, null, 0, 0, 0, codigoDeBarra);
            ///******************************

        if (art == null) {
                System.out.println("Artículo no encontrado");
                mostrarAlerta("", "Codigo inexistente", Alert.AlertType.WARNING);
                return;
            }
            System.out.println("Articulo econtrado: " + art.toString() + "**************************");
            agregarFilaVenta(art);
            txtCodigo.clear();
            txtCantidad.setText("1");

        } catch (NumberFormatException e) {
            System.out.println("Código inválido");
        }
    }

    private void agregarFilaVenta(Articulo art) {

        int cant = Integer.parseInt(txtCantidad.getText());

        // Revisar si ya existe
        for (DetalleVenta d : tablaArticulosVenta.getItems()) {
            if (d.getIdCodArticulo() == art.getIdCodArticulo()) {
                d.setCantidad(d.getCantidad() + cant);
                tablaArticulosVenta.refresh();
                calcularTotal();
                return;
            }
        }

        // Si no existe, agregar nuevo
        DetalleVenta det = new DetalleVenta();
        det.setIdCodArticulo(art.getIdCodArticulo());
        det.setArticulo(art);
        det.setCantidad(cant);
        det.setPrecio(art.getPrecioVenta());

        tablaArticulosVenta.getItems().add(det);
        calcularTotal();
        
    }

    private void calcularTotal() {
        double total = 0;
        for (DetalleVenta d : tablaArticulosVenta.getItems()) {
            total += d.getCantidad() * d.getPrecio();
        }
        System.out.println("tota------------" + total);
        txtTotal.setText(String.format("%.2f", total));
    }

    @FXML
    private void cobrar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/MetodoPago.fxml"));
        Parent root = loader.load();
        MetodoPagoController controller = loader.getController();

        // Pasarle la lista de detalles y el cliente si hace falta
        controller.setListaDetalles(tablaArticulosVenta.getItems());
        controller.setPosController(this); // si quieres llamar grabarVenta desde el dialog

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void confirmarVenta(String MetodoPago) {
        
        if (listaDetalles.isEmpty()) {
            System.out.println("No hay artículos en la venta");
            return;
        }

        String cliente = "Consumidor Final";  // o txtCliente.getText();
        Timestamp fecha = new Timestamp(System.currentTimeMillis());

        VentaDAO dao = new VentaDAOImpl();
        boolean ok = dao.grabarVenta(cliente, fecha, listaDetalles);

        if (ok) {
            System.out.println("Venta registrada con éxito");
            limpiarVenta();
            
        } else {
            System.out.println("Error al registrar venta");
        }
    }

    public void limpiarVenta() {
        tablaArticulosVenta.getItems().clear();
        txtTotal.clear();
        txtCodigo.clear();
        txtCantidad.setText("1");
    }

}
