/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.model;

import java.sql.Timestamp;

/**
 *
 * @author POS
 */
public class Venta {
    
    private int idventa;
    private String cliente;
    private Timestamp fecha;
    private Double totalVenta;
    private String medioPago;

    public Venta() {
    }

    public Venta(int idventa, String cliente, Timestamp fecha, Double totalVenta, String medioPago) {
        this.idventa = idventa;
        this.cliente = cliente;
        this.fecha = fecha;
        this.totalVenta = totalVenta;
        this.medioPago = medioPago;
    }

    
    
    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }
     
    
    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }
    
    
    
}
