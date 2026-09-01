package ni.edu.uam.reto1_inventario.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
}
