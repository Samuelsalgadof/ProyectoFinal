module co.edu.unquindio.proyectofinal.dwr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires org.mapstruct;
    requires javafx.graphics;
    requires java.logging;


    opens co.edu.unquindio.proyectofinal.dwr to javafx.fxml;
    exports co.edu.unquindio.proyectofinal.dwr;
    opens co.edu.unquindio.proyectofinal.dwr.viewController to javafx.fxml;
    exports co.edu.unquindio.proyectofinal.dwr.viewController;
    exports co.edu.unquindio.proyectofinal.dwr.Controller;
    exports co.edu.unquindio.proyectofinal.dwr.mapping.Dto;
    exports co.edu.unquindio.proyectofinal.dwr.mapping.mappers;
    exports co.edu.unquindio.proyectofinal.dwr.model;
    exports co.edu.unquindio.proyectofinal.dwr.exceptions;
    opens co.edu.unquindio.proyectofinal.dwr.Controller to javafx.fxml;
}