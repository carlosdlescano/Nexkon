/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.minegocio.DAO;

import com.minegocio.model.DetalleVenta;
import java.util.ArrayList;

/**
 *
 * @author miNegocio
 */
public interface DetalleVentaDAO {
    public ArrayList<DetalleVenta> buscarVenta(Integer idVenta, Integer idCodArticulo,Double precioMin,Double precioMax);
    public ArrayList<DetalleVenta> buscarVentaNro(Integer idVenta);
}
