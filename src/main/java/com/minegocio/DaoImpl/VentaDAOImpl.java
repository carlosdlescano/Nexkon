/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.minegocio.DAO.VentaDAO;
import com.minegocio.model.DetalleVenta;
import com.minegocio.util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author POS
 */
public class VentaDAOImpl implements VentaDAO {

    private Connection con;
    private SQLServerCallableStatement stmt = null; // se usa el tipo SQLServerCallableStatement para que se pueda enviar la estructura en la posicion 3
    private boolean exito = false;

    public boolean grabarVenta(String cliente, Timestamp fechaVenta, List<DetalleVenta> detalles) {
        con = null;
                
        try {
            con = Conexion.getConexion();
            stmt = (SQLServerCallableStatement)con.prepareCall("{call sp_RegistrarVenta(?, ?, ?)}");

            stmt.setString(1, cliente);
            stmt.setTimestamp(2, fechaVenta);

            SQLServerDataTable tvp = new SQLServerDataTable();
            tvp.addColumnMetadata("idCodArticulo", java.sql.Types.INTEGER);
            tvp.addColumnMetadata("cantidad", java.sql.Types.INTEGER);

            for (DetalleVenta d : detalles) {
                tvp.addRow(d.getIdCodArticulo(), d.getCantidad());
            }

            stmt.setStructured(3, "DetalleVentaType", tvp);
            stmt.execute();
            stmt.close();
            exito = true;
        } catch(SQLException e)  {
            System.out.println("Error al cargar la venta: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return exito;
    }

}

/*Implementacion de metodo en main

public class VentaControlador {

    private VentaDAO ventaDAO;

    public VentaControlador(Connection conn) {
        this.ventaDAO = new VentaDAOImpl(conn);
    }

    public void registrarVenta(String cliente, List<DetalleVenta> detalles) {
        try {
            ventaDAO.grabarVenta(cliente, new Timestamp(System.currentTimeMillis()), detalles);
        } catch (SQLException e) {
            e.printStackTrace(); // o manejo más elegante
        }
    }
}*/

