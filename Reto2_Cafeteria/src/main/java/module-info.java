module ni.edu.uam.reto2_cafeteria {

    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;

    opens ni.edu.uam.reto2_cafeteria.controllers
            to javafx.fxml;

    opens ni.edu.uam.reto2_cafeteria.modelos
            to javafx.base;

    exports ni.edu.uam.reto2_cafeteria;
    exports ni.edu.uam.reto2_cafeteria.controllers;
    exports ni.edu.uam.reto2_cafeteria.modelos;
}