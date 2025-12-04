/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.minegocio.controllerVista;

import com.minegocio.DAO.TelegramConfigDAO;
import com.minegocio.DaoImpl.TelegramConfigDAOImpl;
import com.minegocio.model.TelegramConfig;
import com.minegocio.util.TelegramNotifier;
import static com.minegocio.util.util.mostrarAlerta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author miNegocio
 */
public class ConfiguracionNotificacionesController implements Initializable {

    @FXML
    private CheckBox checkActivo;
    @FXML
    private TextField txtChat;
    @FXML
    private PasswordField txtToken;
    TelegramConfigDAO telegram;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObtenerDatos();

    }

    public void ObtenerDatos() {
        telegram = new TelegramConfigDAOImpl();
        TelegramConfig ultima = telegram.obtenerUltima();
        if (ultima != null) {
            txtChat.setText(ultima.getChatId());
            txtToken.setText(ultima.getToken());
            checkActivo.setSelected(ultima.isActivo());
        }
    }

    public void guardarTel() {
        boolean confirmaNuevo = mostrarAlerta("Confirmación", "¿Esta seguro de guardar configuracón nueva? ", Alert.AlertType.CONFIRMATION, true);
        
        if (!confirmaNuevo) {
            if (txtChat.getText().trim().isEmpty() || txtToken.getText().trim().isEmpty()) {
                mostrarAlerta("", "Debe completar todos los datos antes de guardar!", Alert.AlertType.WARNING, false);
                return;
            }
        } else {
            
            TelegramConfigDAO telegram = new TelegramConfigDAOImpl();
            boolean exito = telegram.insertar(txtToken.getText(), txtChat.getText(), (boolean) checkActivo.isSelected());

            if (exito) {
                mostrarAlerta("", "Se guardo exitosamente la configuracion", Alert.AlertType.INFORMATION, false);
            } else {
                mostrarAlerta("", "Error al guardar la configuración", Alert.AlertType.INFORMATION, false);
            }
        }
    }

    public void probarCoexion() {
        TelegramConfig ultima = new TelegramConfigDAOImpl().obtenerUltima();
        System.out.println("estado de notificacion "+ultima.isActivo());
        System.out.println("ChatID "+ultima.getChatId());
        if (ultima == null) {
            mostrarAlerta("", "No hay configuracion guardada!", Alert.AlertType.WARNING, false);
            return;
        }
        if (!ultima.isActivo()) {
            mostrarAlerta("", "Las notificaciones estan desactivadas", Alert.AlertType.WARNING, false);
            return;
        }

        TelegramNotifier notifier = new TelegramNotifier(ultima.getToken(), ultima.getChatId(), ultima.isActivo());
        boolean exito = notifier.enviarMensaje("Mensaje de prueba desde la aplicación ");

        if (exito) {
            mostrarAlerta("", "Conexión exitosa, mensaje enviado", Alert.AlertType.INFORMATION, false);
        } else {
            mostrarAlerta("", "Error al conectar con Telegram", Alert.AlertType.ERROR, false);
        }

    }
}
/*|||||||||*/
