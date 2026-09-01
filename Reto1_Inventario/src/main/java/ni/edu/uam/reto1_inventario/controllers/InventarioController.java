package ni.edu.uam.reto1_inventario.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.reto1_inventario.models.Producto;

public class InventarioController {

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<Producto> tbProductos;

    @FXML
    private TableColumn<Producto, String> colCode;

    @FXML
    private TableColumn<Producto, String> colName;

    @FXML
    private TableColumn<Producto, Integer> colCantidad;

    @FXML
    private TableColumn<Producto, Double> colPrice;


    @FXML
    public void initialize() {

        colCode.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        colName.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidad")
        );

        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );
    }

    @FXML
    private void agregarProducto(ActionEvent event) {

        String codigo = txtCode.getText().trim();
        String nombre = txtNombre.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();
        String precioTexto = txtPrice.getText().trim();

        if (codigo.isEmpty()
                || nombre.isEmpty()
                || cantidadTexto.isEmpty()
                || precioTexto.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Todos los campos son obligatorios."
            );

            return;
        }

        if (codigoExiste(codigo)) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Código repetido",
                    "Ya existe un producto con el código " + codigo + "."
            );

            return;
        }

        try {

            int cantidad = Integer.parseInt(cantidadTexto);
            double precio = Double.parseDouble(precioTexto);

            if (cantidad < 0) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Cantidad incorrecta",
                        "La cantidad no puede ser negativa."
                );

                return;
            }

            if (precio < 0) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Precio incorrecto",
                        "El precio no puede ser negativo."
                );

                return;
            }

            Producto producto = new Producto(
                    codigo,
                    nombre,
                    cantidad,
                    precio
            );

            tbProductos.getItems().add(producto);


            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Producto agregado",
                    "El producto fue agregado correctamente."
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Datos incorrectos",
                    "La cantidad debe ser un número entero y el precio debe ser numérico."
            );
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {

        if (event.getCode() != KeyCode.ENTER) {
            return;
        }

        String codigoBuscado = txtBuscar.getText().trim();

        if (codigoBuscado.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Código requerido",
                    "Ingrese el código del producto que desea buscar."
            );

            return;
        }

        Producto productoEncontrado = null;

        for (Producto producto : tbProductos.getItems()) {

            if (producto.getCodigo().equalsIgnoreCase(codigoBuscado)) {

                productoEncontrado = producto;
                break;
            }
        }

        if (productoEncontrado == null) {

            tbProductos.getSelectionModel().clearSelection();

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Producto no encontrado",
                    "No existe un producto con el código " + codigoBuscado + "."
            );

            return;
        }

        tbProductos.getSelectionModel().select(productoEncontrado);

        tbProductos.scrollTo(productoEncontrado);

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Producto encontrado",
                "Código: " + productoEncontrado.getCodigo()
                        + "\nNombre: " + productoEncontrado.getNombre()
                        + "\nCantidad disponible: " + productoEncontrado.getCantidad()
                        + "\nPrecio unitario: C$ " + String.format("%.2f", productoEncontrado.getPrecio())
        );
    }

    private boolean codigoExiste(String codigo) {

        for (Producto producto : tbProductos.getItems()) {

            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }

        return false;
    }

    private void limpiarCampos() {

        txtCode.clear();
        txtNombre.clear();
        txtCantidad.clear();
        txtPrice.clear();

        txtCode.requestFocus();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}