/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author POS
 */
public class Conexion {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=TiendaDB; encrypt=true;trustServerCertificate=true;"; //encrypt=true;trustServerCertificate=true; desactiva la validacion de certificado
    private static final String USUARIO = "sa"; 
    private static final String CLAVE = "NexKon";

    public static Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (SQLException e) {
            System.out.println("****Error en la conexión: " + e.getMessage());
        }
        return con;
    }
   
}
