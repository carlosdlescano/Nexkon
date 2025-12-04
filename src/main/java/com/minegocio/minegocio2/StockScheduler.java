/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.minegocio2;

import com.minegocio.DAO.TelegramConfigDAO;
import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.DaoImpl.TelegramConfigDAOImpl;
import com.minegocio.model.TelegramConfig;
import com.minegocio.services.StockService;
import com.minegocio.util.TelegramNotifier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StockScheduler {

    private ScheduledExecutorService scheduler = null;

    public StockScheduler() {
        /*// Configura tu token y chatId de Telegram
        String token = "8537512500:AAGuL3CKXQBE2qb1wSsOsT2shUh-CeUlNGo";
        String chatId = "1335762485";
        
        ArticuloDAOImpl articuloDAO = new ArticuloDAOImpl();
        TelegramNotifier notifier = new TelegramNotifier(token, chatId, true);
        notifier.enviarMensaje("Mensaje de Prueba");
        StockService stockService = new StockService(articuloDAO, notifier);

        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            stockService.verificarStocks();
        }, 0, 30, TimeUnit.MINUTES); // cada 30 minutos
    }

    public void shutdown() {
        scheduler.shutdown();
    }*/
        TelegramConfigDAO telegramDAO = new TelegramConfigDAOImpl();
        TelegramConfig ultima = telegramDAO.obtenerUltima();

        if (ultima != null && ultima.isActivo()) {
            ArticuloDAOImpl articuloDAO = new ArticuloDAOImpl();
            TelegramNotifier notifier = new TelegramNotifier(
                    ultima.getToken(),
                    ultima.getChatId(),
                    ultima.isActivo()
            );

            // Mensaje de prueba
            notifier.enviarMensaje("Scheduler iniciado ");

            StockService stockService = new StockService(articuloDAO, notifier);

            scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(() -> {
                stockService.verificarStocks();
            }, 0, 1, TimeUnit.MINUTES);
        } else {
            System.out.println("No hay configuración activa de Telegram.");
        }

    }
}
