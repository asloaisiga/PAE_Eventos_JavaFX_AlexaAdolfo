module ni.edu.uam.reto1_inventario {
    requires javafx.controls;
    requires javafx.fxml;

    opens ni.edu.uam.reto1_inventario to javafx.fxml;
    opens ni.edu.uam.reto1_inventario.controllers to javafx.fxml;
    opens ni.edu.uam.reto1_inventario.models to javafx.base;
    requires static lombok;

    exports ni.edu.uam.reto1_inventario;
    exports ni.edu.uam.reto1_inventario.controllers;
}