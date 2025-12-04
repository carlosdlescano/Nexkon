/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.DaoImpl;

import com.minegocio.DAO.TelegramConfigDAO;
import com.minegocio.model.TelegramConfig;
import com.minegocio.util.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miNegocio
 */
public class TelegramConfigDAOImpl implements TelegramConfigDAO{

    @Override
    public boolean insertar(String token, String chatId, boolean activo) {
        Connection con = null;
        CallableStatement stmt = null;
        boolean exito = false;

        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call sp_insert_config(?, ?, ?)}");

            stmt.setString(1, token); // clave primaria
            stmt.setString(2, chatId);   // se puede modificar
            stmt.setBoolean(3, activo);
            
            int filas = stmt.executeUpdate(); // ejecuta el SP
            exito = (filas > 0);
            

        } catch (SQLException e) {
            System.err.println("Error al actualizar artículo: " + e.getMessage());
            exito = false;
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
    

    @Override
    public TelegramConfig obtenerUltima() {
        Connection con = null;
        CallableStatement stmt = null;
        TelegramConfig tel = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConexion();
            stmt = con.prepareCall("{call sp_get_last_config}");

            rs = stmt.executeQuery();
            if(rs.next()){
                tel = new TelegramConfig();
                tel.setChatId(rs.getString("chat_id"));
                tel.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
                tel.setId(rs.getInt("id"));
                tel.setToken(rs.getString("token"));
                tel.setActivo(rs.getBoolean("activo"));
                
            }
            

        } catch (SQLException e) {
            System.err.println("Error al buscar datos: " + e.getMessage());
            
        } finally {
            try {
                if (rs != null) rs.close();
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
        return tel;
    }
    
}
