/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.DAO.*;
import com.minegocio.DaoImpl.*;
import com.minegocio.model.*;
import com.minegocio.util.DatosCompartidos;
import com.minegocio.util.util;
import static com.minegocio.util.util.formatDouble;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static com.minegocio.util.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class VistaArticulosController implements Initializable, DatosCompartidos {

    private List<Departamento> departamentos;//variable de datoscompartidos
    private List<Familia> familias;
    private List<Rubro> rubros;
    private List<Marca> marcas;
    private boolean esEditado = true;

    @FXML
    private TableView<Articulo> TablaArticulos;
    @FXML
    private TableColumn<Articulo, String> ColumDescripcion;
    @FXML
    private TableColumn<Articulo, Number> columCodigo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtCodMarca;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtCodigoBarra;
    @FXML
    private TextField txtPrecioActual;
    @FXML
    private TextField txtMargen;
    @FXML
    private TextField txtPrecioCosto;
    @FXML
    private TextField txtStock;

    @FXML
    private ChoiceBox<String> cboxRubro;
    @FXML
    private ChoiceBox<String> cboxDepartamento;
    @FXML
    private ChoiceBox<String> cboxFamilia;
    @FXML
    private ChoiceBox<String> cboxMarca;

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatInteger(txtCodigo);
        formatInteger(txtCodMarca);
        formatInteger(txtCodigoBarra);
        formatDouble(txtMargen);
        formatDouble(txtPrecioActual);
        formatDouble(txtPrecioCosto);
        formatInteger(txtStock);

        habilitarEdicion(false);

        txtPrecioActual.setDisable(true);
        txtCodMarca.setEditable(false);

        // Política de ajuste automático
        TablaArticulos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        // Proporciones deseadas 
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
        txtCodMarca.setText(String.valueOf(marcas.get(a.getMarca() - 1).getCodMarca()));
        txtCodigo.setText(String.valueOf(a.getCodigo()));
        txtCodigoBarra.setText(String.valueOf(a.getCodigoBarra()));
        txtMargen.setText(String.valueOf(a.getMargen()));
        txtPrecioActual.setText(String.valueOf(a.getPrecioVenta()));
        txtPrecioCosto.setText(String.valueOf(a.getPrecioCosto()));
        txtStock.setText(String.valueOf(a.getStock()));
        String rubroSeleccionado = rubros.get(a.getCodRubro() - 1).getDescripcion();
        cboxRubro.setValue(rubroSeleccionado);
        String depaSelect = departamentos.get(a.getCodDepartamento() - 1).getDescripcion();
        cboxDepartamento.setValue(depaSelect);
        String famSelect = familias.get(a.getCodFamilia() - 1).getDescripcion();
        cboxFamilia.setValue(famSelect);
        String marcaSelect = marcas.get(a.getMarca() - 1).getDescripcion();
        cboxMarca.setValue(marcaSelect);

    }

    @Override
    public void setDatos(List<Departamento> departamentos, List<Familia> familias, List<Rubro> rubros, List<Marca> marcas) {
        this.departamentos = departamentos;
        this.familias = familias;
        this.rubros = rubros;
        this.marcas = marcas;

        // Limpiar por si ya tenía datos
        cboxRubro.getItems().clear();
        cboxDepartamento.getItems().clear();
        cboxFamilia.getItems().clear();
        cboxRubro.getItems().clear();
        // Recorrer la lista y agregar las descripciones
        for (Rubro r : rubros) {
            cboxRubro.getItems().add(r.getDescripcion());
        }
        for (Departamento d : departamentos) {
            cboxDepartamento.getItems().add(d.getDescripcion());
        }
        for (Familia f : familias) {
            cboxFamilia.getItems().add(f.getDescripcion());
        }
        for (Marca m : marcas) {
            cboxMarca.getItems().add(m.getDescripcion());
        }

    }

    private void limpiarDetalles() {
        txtDescripcion.clear();
        txtCodMarca.clear();
        txtCodigo.clear();
        txtCodigoBarra.clear();
        txtPrecioActual.clear();
        txtMargen.clear();
        txtPrecioCosto.clear();
        txtStock.clear();

        cboxRubro.setValue(null);
        cboxDepartamento.setValue(null);
        cboxFamilia.setValue(null);
        cboxMarca.setValue(null);
    }

    public void habilitarEdicion(boolean band) {
        txtDescripcion.setEditable(band);
        txtCodigo.setEditable(band);
        txtCodigoBarra.setEditable(band);
        txtMargen.setEditable(band);
        txtPrecioCosto.setEditable(band);
        txtStock.setEditable(band);
        TablaArticulos.setDisable(band);

        cboxRubro.setMouseTransparent(!band);
        cboxRubro.setFocusTraversable(band);
        cboxDepartamento.setMouseTransparent(!band);
        cboxDepartamento.setFocusTraversable(band);
        cboxFamilia.setMouseTransparent(!band);
        cboxFamilia.setFocusTraversable(band);
        cboxMarca.setMouseTransparent(!band);
        cboxMarca.setFocusTraversable(band);

        btnCancelar.setDisable(!band);
        btnGuardar.setDisable(!band);
    }

    public void editarArticulo() {
        habilitarEdicion(true);
        esEditado=true;
    }

    public void cancelarEdicion() {
        habilitarEdicion(false);
        limpiarDetalles();

    }

    public void guardarArticulo() {
        ArticuloDAO artdao = new ArticuloDAOImpl();

        if (esEditado) {
            Articulo articuloSeleccionado = TablaArticulos.getSelectionModel().getSelectedItem();
            Articulo artedit = new Articulo();
            if (articuloSeleccionado != null) {
                artedit.setIdCodArticulo(articuloSeleccionado.getIdCodArticulo());
                artedit.setCodDepartamento(cboxDepartamento.getSelectionModel().getSelectedIndex() + 1);
                artedit.setCodFamilia(cboxFamilia.getSelectionModel().getSelectedIndex() + 1);
                artedit.setCodRubro(cboxRubro.getSelectionModel().getSelectedIndex() + 1);
                artedit.setCodigo(Integer.parseInt(txtCodigo.getText()));
                artedit.setCodigoBarra(Long.parseLong(txtCodigoBarra.getText()));
                artedit.setDescripcion(txtDescripcion.getText());
                artedit.setMarca(cboxMarca.getSelectionModel().getSelectedIndex() + 1);
                artedit.setMargen(Double.parseDouble(txtMargen.getText()));
                artedit.setPrecioCosto(Double.parseDouble(txtPrecioCosto.getText()));
                artedit.setPrecioVenta(Double.parseDouble(txtPrecioActual.getText()));
                artedit.setStock(Integer.parseInt(txtStock.getText()));
            }
            if (artdao.actualizarArticulo(artedit)) {
                System.out.println("Actualizacion completa");
                TablaArticulos.refresh();
                mostrarAlerta("Edicion de articulo", "La edición fue exitosa!", Alert.AlertType.CONFIRMATION,false);
                // 🔹 Reemplazar solo la fila seleccionada en el TableView
                int index = TablaArticulos.getSelectionModel().getSelectedIndex();
                TablaArticulos.getItems().set(index, artedit); // actualiza visualmente la fila

                // Mantener la selección
                TablaArticulos.getSelectionModel().select(index);
                TablaArticulos.scrollTo(index);
            } else {
                System.out.println("Error al actualizar artículo");
            }
        }//fin edicion
        else {
            Articulo nuevoArticulo = new Articulo();

            // Tomar los valores de los campos del formulario
            String descripcion = txtDescripcion.getText();
            int codigo = Integer.parseInt(txtCodigo.getText());
            long codigoBarra = Long.parseLong(txtCodigoBarra.getText());
            double margen = Double.parseDouble(txtMargen.getText());
            double precioCosto = Double.parseDouble(txtPrecioCosto.getText());
            int stock = Integer.parseInt(txtStock.getText());

            int codRubro = cboxRubro.getSelectionModel().getSelectedIndex() + 1;
            int codDepartamento = cboxDepartamento.getSelectionModel().getSelectedIndex() + 1;
            int codFamilia = cboxFamilia.getSelectionModel().getSelectedIndex() + 1;
            int codMarca = cboxMarca.getSelectionModel().getSelectedIndex() + 1;
            double precioVenta = Double.parseDouble(txtPrecioActual.getText());
            // Asignar estos valores al nuevo artículo
            nuevoArticulo.setDescripcion(descripcion);
            nuevoArticulo.setCodigo(codigo);
            nuevoArticulo.setCodigoBarra(codigoBarra);
            nuevoArticulo.setPrecioVenta(precioVenta);
            nuevoArticulo.setMargen(margen);
            nuevoArticulo.setPrecioCosto(precioCosto);
            nuevoArticulo.setStock(stock);
            nuevoArticulo.setCodRubro(codRubro);
            nuevoArticulo.setCodDepartamento(codDepartamento);
            nuevoArticulo.setCodFamilia(codFamilia);
            nuevoArticulo.setMarca(codMarca);

            ArticuloDAO articuloDAO = new ArticuloDAOImpl();
            boolean articuloGuardado = articuloDAO.crearArticulo(nuevoArticulo);

            if (articuloGuardado) {
                System.out.println("Artículo guardado exitosamente.");
                cargarTabla();
                TablaArticulos.refresh();
                mostrarAlerta("Articulo Nuevo", "El articulo se cargo exitosamente!", Alert.AlertType.CONFIRMATION,false);
               
            } else {
                System.out.println("Error al guardar el artículo.");
            }

            limpiarDetalles();

        }
        habilitarEdicion(false);

    }

    public void actualizarPrecio() {
        try {

            double margen = Double.parseDouble(txtMargen.getText());
            System.out.println("Margen " + margen);
            double costo = Double.parseDouble(txtPrecioCosto.getText());
            System.out.println("Precio " + costo);
            // double precioVenta = costo * (1 + margen / 100); // ejemplo: margen en %
            double precioVenta = costo * (1 + margen / 100);
            System.out.println("Precio venta " + precioVenta);
            txtPrecioActual.setText(String.valueOf(precioVenta));

        } catch (NumberFormatException e) {
            System.out.println(" " + e.toString());
            txtPrecioActual.setText("0"); // o dejar vacío
        }

    }

    public void nuevoArticulo() {

        limpiarDetalles();
        habilitarEdicion(true);
        esEditado = false;

    }

}
