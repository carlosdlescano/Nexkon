
import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.model.Articulo;
import com.minegocio.services.StockService;
import com.minegocio.util.TelegramNotifier;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miNegocio
 */
public class TestNotifier {
    public static void main(String[] args) {
        // Usa tu token y chatId reales
        String token = "8537512500:AAGuL3CKXQBE2qb1wSsOsT2shUh-CeUlNGo";
        String chatId = "-1003438239101";//"-1003308683517";
        //String chatId = "1335762485"; // tu chatId obtenido con getUpdates

        TelegramNotifier notifier = new TelegramNotifier(token, chatId);

        // Mensaje de prueba
        notifier.enviarMensaje("Prueba exitosa!");
        
        ArticuloDAO art = new ArticuloDAOImpl();
        
        StockService ser = new StockService((ArticuloDAOImpl) art, notifier);
        ser.verificarStocks();
    }
}
