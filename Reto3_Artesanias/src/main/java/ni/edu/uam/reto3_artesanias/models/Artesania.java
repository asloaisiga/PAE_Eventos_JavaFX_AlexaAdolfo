package ni.edu.uam.reto3_artesanias.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Artesania {
    private String codigo;
    private String nombre;
    private String tipo;
    private double precio;
    private String rutaImagen;
}
