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
