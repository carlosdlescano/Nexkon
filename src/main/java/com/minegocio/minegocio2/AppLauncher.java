/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.minegocio2;

import com.minegocio.services.StockService;

/**
 *
 * @author POS
 */
public class AppLauncher {

    public static void main(String[] args) {
        App.main(args);
        
        new StockScheduler();
        
          // // se ejecuta al cerrar la app
            //scheduler.shutdown();

        
        
    }

}
