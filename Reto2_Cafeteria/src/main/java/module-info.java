module ni.edu.uam.reto2_cafeteria {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.reto2_cafeteria to javafx.fxml;
    exports ni.edu.uam.reto2_cafeteria;
}