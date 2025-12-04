/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.services;

import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.model.Articulo;
import com.minegocio.util.TelegramNotifier;
import java.util.List;

public class StockService {

    private final ArticuloDAOImpl articuloDAO;
    private final TelegramNotifier notifier;

    public StockService(ArticuloDAOImpl articuloDAO, TelegramNotifier notifier) {
        this.articuloDAO = articuloDAO;
        this.notifier = notifier;
    }

    public void verificarStocks() {
        List<Articulo> articulos = articuloDAO.listarTodos();

        for (Articulo articulo : articulos) {
            int stock = articulo.getStock();
            int critico = articulo.getStockCritico();

            if (stock <= critico) {
                notifier.enviarMensaje("⚠️ Stock en riesgo: " + articulo.getDescripcion() +
                        " (Stock actual: " + stock + ")");
            }

            if (stock <= critico * 0.5) {
                int sugerido = critico + 10; // ejemplo: sugerir 10 unidades más que el crítico
                notifier.enviarMensaje("🚨 Comprar artículo: " + articulo.getDescripcion() +
                        " (Stock actual: " + stock + "). Cantidad sugerida: " + sugerido);
            }
        }
    }
}

