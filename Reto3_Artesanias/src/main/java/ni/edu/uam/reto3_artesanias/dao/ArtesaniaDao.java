package ni.edu.uam.reto3_artesanias.dao;

import ni.edu.uam.reto3_artesanias.models.Artesania;

import java.util.ArrayList;
import java.util.List;

public class ArtesaniaDao {

    List<Artesania> artesanias;

    public ArtesaniaDao() {
        artesanias = new ArrayList<>();
    }

    public void agregar(Artesania artesania) {
        artesanias.add(artesania);
    }

    public List<Artesania> obtenerRegistros() {
        return artesanias;
    }

    public Artesania buscarPorCodigo(String codigo) {

        for (Artesania artesania : artesanias) {

            if (artesania.getCodigo().equals(codigo)) {
                return artesania;
            }
        }

        return null;
    }
}


