module ni.edu.uam.reto1_inventario {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.reto1_inventario to javafx.fxml;
    exports ni.edu.uam.reto1_inventario;
}