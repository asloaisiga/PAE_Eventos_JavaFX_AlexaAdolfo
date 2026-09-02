module ni.edu.uam.reto3_artesanias {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.reto3_artesanias to javafx.fxml;
    exports ni.edu.uam.reto3_artesanias;
}