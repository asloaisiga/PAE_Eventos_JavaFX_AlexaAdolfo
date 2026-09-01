package ni.edu.uam.reto1_inventario.dao;

import ni.edu.uam.reto1_inventario.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    List<Producto> productos;

    public ProductoDao() {
        productos = new ArrayList<>();
    }

    public void agregar(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> obtenerRegistros() {
        return productos;
    }

    public Producto buscarPorCodigo(String codigo) {

        for (Producto producto : productos) {

            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }

        return null;
    }
}