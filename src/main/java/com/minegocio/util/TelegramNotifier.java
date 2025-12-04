/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TelegramNotifier {

    private final String token;
    private final String chatId;
    private final boolean activo;

    public TelegramNotifier(String token, String chatId, boolean activo) {
        this.token = token;
        this.chatId = chatId;
        this.activo = activo;
    }

    public boolean enviarMensaje(String mensaje) {
        try {
            if (activo) {
                String urlString = "https://api.telegram.org/bot" + token
                        + "/sendMessage?chat_id=" + chatId
                        + "&text=" + URLEncoder.encode(mensaje, "UTF-8");
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.getInputStream(); // dispara la petición

                int responseCode = conn.getResponseCode(); // obtenemos el código HTTP

                // Si la API responde 200 OK, consideramos que fue exitoso
                return responseCode == HttpURLConnection.HTTP_OK;
            }else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
