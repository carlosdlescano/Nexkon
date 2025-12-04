package com.minegocio.minegocio2;


import com.minegocio.DAO.ArticuloDAO;
import com.minegocio.DaoImpl.ArticuloDAOImpl;
import com.minegocio.model.Articulo;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/Vistas/VistaLogin"), 200, 250);//carga vista principal
        scene.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());//carga estilos
        
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));        
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
       launch();
        
    }
    

}