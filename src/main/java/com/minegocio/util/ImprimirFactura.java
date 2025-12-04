///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.minegocio.util;
//
///**
// *
// * @author miNegocio
// */
//import com.minegocio.model.*;
////import static com.minegocio.util.util.mostrarAlerta;
//import java.awt.Desktop;
//import java.io.File;
//import java.util.List;
//
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//
//public class ImprimirFactura {
//
//    private Venta venta;
//    private List<DetalleVenta> detalles;
//
//    // Constructor recibe la venta y su detalle
//    public ImprimirFactura(Venta venta, List<DetalleVenta> detalles) {
//        this.venta = venta;
//        this.detalles = detalles;
//    }
//
//    // Método principal: muestra la opción y ejecuta impresión
//    public void ejecutar() {
//
//        // Definimos los botones para elegir formato
//        ButtonType btnWord = new ButtonType("Word");
//        ButtonType btnPDF = new ButtonType("PDF");
//        ButtonType btnCancelar = new ButtonType("Cancelar", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);
//
//        // Mostramos el diálogo usando tu clase Util
//        ButtonType eleccion = util.mostrarOpciones(
//                "Imprimir factura",
//                "Seleccione el formato de impresión:",
//                Alert.AlertType.CONFIRMATION,
//                btnWord, btnPDF, btnCancelar
//        );
//
//        // Ejecutamos según la elección del usuario
//        if (eleccion == btnWord) {
//            imprimirWord();
//        } else if (eleccion == btnPDF) {
//            imprimirPDF();
//        } else {
//            System.out.println("Operación cancelada");
//        }
//    }
//
//    // --- Genera e imprime Word (.docx) ---
//    private void imprimirWord() {
//        try {
//            String nombreArchivo = "factura_" + venta.getIdventa() + ".docx";
//
//            // Generamos el archivo Word usando la clase GeneradorFacturaWord
//            GeneradorFacturaWord gen = new GeneradorFacturaWord();
//            gen.generarFactura(
//                    venta,
//                    detalles,
//                    "plantillas/factura_template.docx", // ruta a tu plantilla
//                    nombreArchivo
//            );
//
//            // Abrimos automáticamente el archivo en Word
//            Desktop.getDesktop().open(new File(nombreArchivo));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            util.mostrarAlerta("Error", "No se pudo generar el Word", Alert.AlertType.ERROR, false);
//        }
//    }
//
//    // --- Genera e imprime PDF (.pdf) ---
//    private void imprimirPDF() {
//        try {
//            String docx = "factura_" + venta.getIdventa() + ".docx";
//            String pdf = "factura_" + venta.getIdventa() + ".pdf";
//
//            // Primero generamos el Word
//            GeneradorFacturaWord gen = new GeneradorFacturaWord();
//            gen.generarFactura(
//                    venta,
//                    detalles,
//                    "plantillas/factura_template.docx", // ruta a tu plantilla
//                    docx
//            );
//
//            // Convertimos a PDF usando LibreOffice en modo headless
//            ProcessBuilder pb = new ProcessBuilder(
//                    "soffice", "--headless",
//                    "--convert-to", "pdf",
//                    docx,
//                    "--outdir", "."
//            );
//            pb.start().waitFor(); // esperamos que termine la conversión
//
//            // Abrimos automáticamente el PDF
//            Desktop.getDesktop().open(new File(pdf));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            util.mostrarAlerta("Error", "No se pudo generar el PDF", Alert.AlertType.ERROR, false);
//        }
//    }
//}
