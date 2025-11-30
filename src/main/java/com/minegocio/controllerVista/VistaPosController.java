/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.model.Articulo;
import com.minegocio.model.DetalleVenta;
import static com.minegocio.util.util.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author POS
 */
public class VistaPosController implements Initializable {

    @FXML
    private TextField txtCodigo;
    
    @FXML
    private TextField txtCantidad;
    @FXML
    private TableView<DetalleVenta> tablaArticulosVenta;
    //@FXML
    //private TextField

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatInteger(txtCantidad);
    }

    private void agregarArticuloPorCodigo() {
        String texto = txtCodigo.getText().trim();

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
                return;
            }

            agregarFilaVenta(art);
            txtCodigo.clear();

        } catch (NumberFormatException e) {
            System.out.println("Código inválido");
        }
    }

    private void agregarFilaVenta(Articulo art) {
        DetalleVenta det = new DetalleVenta();
        int cant = Integer.parseInt(txtCantidad.getText());
        
        det.setIdCodArticulo(art.getIdCodArticulo());
        det.setCantidad(cant);
        det.setPrecio(art.getPrecioVenta());

        tablaArticulosVenta.getItems().add(det);
    }

}
