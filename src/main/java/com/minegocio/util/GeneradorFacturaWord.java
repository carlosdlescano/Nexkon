//package com.minegocio.util;
//
//import com.minegocio.model.*;
//import java.io.File;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.HashMap;
//import java.util.Map;
//
////import org.docx4j.openpackaging.exceptions.Docx4JException;
////import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
////import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
////import org.docx4j.wml.Tbl;
////import org.docx4j.wml.Tr;
////import org.docx4j.wml.Tc;
////import org.docx4j.XmlUtils;
//
///**
// * Clase para generar facturas en formato Word (.docx) a partir de una
// * plantilla usando Docx4j.
// */
//public class GeneradorFacturaWord {
//
//    /**
//     * Genera la factura en Word a partir de una plantilla.
//     *
//     * @param venta Venta principal (cabecera)
//     * @param detalles Lista de DetalleVenta (artículos)
//     * @param plantilla Ruta del archivo plantilla .docx
//     * @param salida Ruta donde guardar el archivo generado
//     * @throws Exception Si ocurre un error al leer/escribir archivos
//     */
//    public void generarFactura(Venta venta, List<DetalleVenta> detalles,
//                               String plantilla, String salida) throws Exception {
//
//        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.load(new File(plantilla));
//        MainDocumentPart mainPart = wordPackage.getMainDocumentPart();
//
//        // Reemplazamos marcadores simples
//        Map<String, String> marcadores = new HashMap<>();
//        marcadores.put("ID_VENTA", String.valueOf(venta.getIdventa()));
//        marcadores.put("CLIENTE", venta.getCliente());
//        marcadores.put("TOTAL", String.format("$ %.2f", venta.getTotalVenta()));
//
//        if (venta.getFecha() != null) {
//            String fecha = venta.getFecha().toLocalDateTime()
//                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
//            marcadores.put("FECHA", fecha);
//        }
//
//        mainPart.variableReplace(marcadores);
//
//        // Completamos la tabla de detalle
//        completarTablaDetalle(wordPackage, detalles);
//
//        // Guardamos el documento
//        wordPackage.save(new File(salida));
//    }
//
//    /**
//     * Llena la tabla de detalle con los artículos de la venta. Asume que la
//     * tabla de detalle es la segunda tabla del documento.
//     */
//    private void completarTablaDetalle(WordprocessingMLPackage wordPackage, List<DetalleVenta> detalles) throws Docx4JException {
//       // MainDocumentPart mainPart = wordPackage.getMainDocumentPart();
//        List<Object> tables = mainPart.getJAXBNodesViaXPath("//w:tbl", true);
//
//        if (tables.size() < 2) {
//            System.out.println("La plantilla no tiene tabla de detalle.");
//            return;
//        }
//
//        //Tbl tabla = (Tbl) XmlUtils.unwrap(tables.get(1));
//
//        // Eliminamos las filas antiguas (excepto la primera)
//        List<Object> filas = tabla.getContent();
//        while (filas.size() > 1) {
//            filas.remove(1);
//        }
//
//        // Agregamos filas por cada detalle
//        for (DetalleVenta det : detalles) {
//            //Tr nuevaFila = (Tr) XmlUtils.deepCopy(filas.get(0));
//
//            // Asignamos valores
//            List<Object> celdas = nuevaFila.getContent();
////            ((Tc)celdas.get(0)).getContent().clear();
////            ((Tc)celdas.get(0)).getContent().add(mainPart.createParagraphOfText(
////                    det.getArticulo() != null ? det.getArticulo().getDescripcion() : "Sin descripción"));
////
////            ((Tc)celdas.get(1)).getContent().clear();
////            ((Tc)celdas.get(1)).getContent().add(mainPart.createParagraphOfText(String.valueOf(det.getCantidad())));
////
////            ((Tc)celdas.get(2)).getContent().clear();
////            ((Tc)celdas.get(2)).getContent().add(mainPart.createParagraphOfText(String.format("$ %.2f", det.getPrecio())));
////
////            ((Tc)celdas.get(3)).getContent().clear();
////            ((Tc)celdas.get(3)).getContent().add(mainPart.createParagraphOfText(String.format("$ %.2f", det.getSubtotal())));
//
//            tabla.getContent().add(nuevaFila);
//        }
//    }
//}
