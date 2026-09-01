package ni.edu.uam.reto2_cafeteria.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.reto2_cafeteria.modelos.Cafe;

public class CafeDao {
    private final ObservableList<Cafe> listaCafe;

    public CafeDao() {
        listaCafe = FXCollections.observableArrayList();
    }

    public ObservableList<Cafe> obtenerLotes() {
        return listaCafe;
    }

    public void agregar(Cafe cafe) {
        listaCafe.add(cafe);
    }

    public void actualizar(Cafe cafeAnterior, Cafe cafeNuevo) {

        int posicion = listaCafe.indexOf(cafeAnterior);

        if (posicion != -1) {
            listaCafe.set(posicion, cafeNuevo);
        }
    }

    public void eliminar(Cafe cafe) {
        listaCafe.remove(cafe);
    }
}
