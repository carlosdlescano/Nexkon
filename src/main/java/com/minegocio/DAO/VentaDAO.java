/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DAO;

import com.minegocio.model.DetalleVenta;
import com.minegocio.model.Venta;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author POS
 */
public interface VentaDAO {
    
    public boolean grabarVenta(String cliente, Timestamp fechaVenta, String medioPago, List<DetalleVenta> detalles);
    public ArrayList<Venta> buscarVenta(Timestamp fechaInicio, Timestamp fechaFin, String cliente, String medioPago);
    
    
}
