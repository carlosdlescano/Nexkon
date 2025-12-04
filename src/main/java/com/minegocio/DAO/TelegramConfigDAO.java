/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.minegocio.DAO;

import com.minegocio.model.TelegramConfig;

/**
 *
 * @author miNegocio
 */
public interface TelegramConfigDAO {
    
    public boolean insertar (String token, String chatId, boolean activo);
    public TelegramConfig obtenerUltima();
    
}
