module com.minegocio.minegocio2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires java.base;

    // Tu clase App está acá
    opens com.minegocio.minegocio2 to javafx.fxml;
    exports com.minegocio.minegocio2;

    // Tus controladores están acá
    opens com.minegocio.controllerVista to javafx.fxml;
    opens com.minegocio.model to javafx.base, javafx.fxml;
    exports com.minegocio.controllerVista;
    
     
   
}
/*
module com.minegocio.minegocio2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.microsoft.sqlserver.jdbc;

//// Requiere acceso al "unnamed module" para usar Docx4j
//    requires org.docx4j;
    // Exportamos los paquetes que se usan desde otros módulos o FXML
    exports com.minegocio.minegocio2;
    exports com.minegocio.controllerVista;


//    requires javafx.controls;
//    requires javafx.fxml;
//        // Dependencia POI
//    requires poi.ooxml;
//
//
//    requires java.sql;
//    requires com.microsoft.sqlserver.jdbc;
//    requires java.desktop;
//
//    //opens com.minegocio.minegocio2 to javafx.fxml;
//    exports com.minegocio.minegocio2;
//
//    //opens com.minegocio.controllerVista to javafx.fxml;
//    //opens com.minegocio.model to javafx.base, javafx.fxml;
//    exports com.minegocio.controllerVista;
}
*/