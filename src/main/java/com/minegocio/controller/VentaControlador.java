/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.controller;

import com.minegocio.DAO.VentaDAO;
import com.minegocio.model.DetalleVenta;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author POS
 */
public class VentaControlador {
   private VentaDAO ventaDAO;

    public VentaControlador() {
        // El controlador se encarga de obtener la conexión y crear el DAO 
    }
    
    public boolean registrarVenta(String cliente, List<DetalleVenta> detalles) {
        if (cliente == null || cliente.trim().isEmpty()) {
            System.out.println("Cliente no válido.");
            return false;
        }

        if (detalles == null || detalles.isEmpty()) {
            System.out.println("No se puede registrar una venta sin artículos.");
            return false;
        }

        Timestamp fechaVenta = new Timestamp(System.currentTimeMillis());

        return ventaDAO.grabarVenta(cliente, fechaVenta, detalles);
    }

}

