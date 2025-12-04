/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

/**
 *
 * @author miNegocio
 */
public class util {

    public static void formatInteger(TextField txtText) {
        TextFormatter<Integer> format = new TextFormatter<>(change -> {
            String nuevoTexto = change.getControlNewText();
            if (nuevoTexto.matches("\\d*")) {//solo digitos
                return change;
            }
            return null;//rechaza cambio
        });
        txtText.setTextFormatter(format);
    }

    public static void formatDouble(TextField txtText) {
        TextFormatter<Double> formatDecimal = new TextFormatter<>(change -> {
            String nuevoTexto = change.getControlNewText();
            if (nuevoTexto.matches("\\d*(\\.\\d{0,2})?")) {//("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        });

        txtText.setTextFormatter(formatDecimal);
    }

    public static boolean mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo, boolean esConfirmacion) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        if (esConfirmacion) {
            Optional<ButtonType> result = alert.showAndWait();
            return result.isPresent() && result.get() == ButtonType.OK;
        } else {
            alert.showAndWait();
            return true; // solo para mantener el tipo boolean, aunque no importa
        }
    }

    public static ButtonType mostrarOpciones(
            String titulo,
            String mensaje,
            Alert.AlertType tipo,
            ButtonType... opciones) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.getButtonTypes().setAll(opciones); // ponemos los botones que queremos

        Optional<ButtonType> result = alert.showAndWait();
        return result.orElse(ButtonType.CANCEL);
    }

}
