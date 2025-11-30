/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.model;

/**
 *
 * @author POS
 */
public class DetalleVenta {
    private int idDetalle;
    private int idVenta;
    private int idCodArticulo;
    private int cantidad;
    private double precio;
    private Articulo articulo;

    public DetalleVenta() {
    }

    public DetalleVenta(int idCodArticulo, int cantidad) {
        this.idCodArticulo = idCodArticulo;
        this.cantidad = cantidad;
    }

    public DetalleVenta(int cantidad, Articulo articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
    
    

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCodArticulo() {
        return idCodArticulo;
    }

    public void setIdCodArticulo(int idCodArticulo) {
        this.idCodArticulo = idCodArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
            
            
            
    
}
