module ni.edu.uam.reto3_artesanias {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens ni.edu.uam.reto3_artesanias to javafx.fxml;
    opens ni.edu.uam.reto3_artesanias.controllers to javafx.fxml;
    opens ni.edu.uam.reto3_artesanias.models to javafx.base;

    exports ni.edu.uam.reto3_artesanias;
    exports ni.edu.uam.reto3_artesanias.controllers;
}
