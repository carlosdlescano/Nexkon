/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.model;

import java.sql.Timestamp;

/**
 *
 * @author miNegocio
 */
public class TelegramConfig {
    private int id;
    private String token;
    private String chatId;
    private Timestamp fechaActualizacion;
    private boolean activo;

    public TelegramConfig(int id, String token, String chatId, Timestamp fechaActualizacion) {
        this.id = id;
        this.token = token;
        this.chatId = chatId;
        this.fechaActualizacion = fechaActualizacion;
    }

    public TelegramConfig(String token, String chatId, boolean activo) {
        this.token = token;
        this.chatId = chatId;
        this.activo = activo;
    }

    public TelegramConfig() {
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    
    
}
