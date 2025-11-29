/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.util;

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
    
    
}
