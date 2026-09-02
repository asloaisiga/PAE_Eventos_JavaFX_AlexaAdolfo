package ni.edu.uam.reto2_cafeteria.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cafe {
    private String lote;
    private String productor;
    private double cantidad;

}
