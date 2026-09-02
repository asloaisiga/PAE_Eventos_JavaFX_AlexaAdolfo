package ni.edu.uam.reto3_artesanias.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ni.edu.uam.reto3_artesanias.dao.ArtesaniaDao;
import ni.edu.uam.reto3_artesanias.models.Artesania;

import java.io.File;

public class ArtesaniaController {

    ArtesaniaDao listado = new ArtesaniaDao();

    private String rutaImagen;

    @FXML
    private MenuItem itmCatalogo;

    @FXML
    private MenuItem itmVentas;

    @FXML
    private MenuItem itmAyuda;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnSearch;

    @FXML
    private ImageView imgPrevia;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cbTipo;

    @FXML
    private TextField txtPrecio;

    @FXML
    private Button btnImagen;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<Artesania> tbProductos;

    @FXML
    private TableColumn<Artesania, ImageView> colImagen;

    @FXML
    private TableColumn<Artesania, String> colCodigo;

    @FXML
    private TableColumn<Artesania, String> colNombre;

    @FXML
    private TableColumn<Artesania, String> colTipo;

    @FXML
    private TableColumn<Artesania, Double> colPrecio;

    @FXML
    public void initialize() {

        cbTipo.getItems().addAll(
                "Cerámica",
                "Madera",
                "Textil",
                "Cuero",
                "Joyería",
                "Decoración",
                "Otros"
        );

        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colImagen.setCellValueFactory(datos -> {

            ImageView imagen = new ImageView();

            imagen.setFitWidth(55);
            imagen.setFitHeight(55);
            imagen.setPreserveRatio(true);

            String ruta = datos.getValue().getRutaImagen();

            if (ruta != null) {

                File archivo = new File(ruta);

                if (archivo.exists()) {
                    imagen.setImage(
                            new Image(archivo.toURI().toString())
                    );
                }
            }

            return new ReadOnlyObjectWrapper<>(imagen);
        });
    }

    @FXML
    private void abrirCatalogo(ActionEvent event) {

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Catálogo",
                "Actualmente se encuentra en el catálogo de artesanías."
        );
    }

    @FXML
    private void abrirVentas(ActionEvent event) {

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Ventas",
                "Sección de ventas de artesanías."
        );
    }

    @FXML
    private void abrirAyuda(ActionEvent event) {

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Ayuda",
                "Puede registrar, guardar y buscar artesanías."
        );
    }

    @FXML
    private void nuevoProducto(ActionEvent event) {

        limpiarCampos();
    }

    @FXML
    private void seleccionarImagen(ActionEvent event) {

        FileChooser selector = new FileChooser();

        selector.setTitle("Seleccionar imagen");

        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        File archivo = selector.showOpenDialog(
                btnImagen.getScene().getWindow()
        );

        if (archivo != null) {

            rutaImagen = archivo.getAbsolutePath();

            imgPrevia.setImage(
                    new Image(archivo.toURI().toString())
            );
        }
    }

    @FXML
    private void guardarProducto(ActionEvent event) {

        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String tipo = cbTipo.getValue();
        String precioTexto = txtPrecio.getText().trim();

        if (codigo.isEmpty()
                || nombre.isEmpty()
                || tipo == null
                || precioTexto.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Todos los campos son obligatorios."
            );

            return;
        }

        if (listado.buscarPorCodigo(codigo) != null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Código repetido",
                    "Ya existe una artesanía con ese código."
            );

            return;
        }

        try {

            double precio = Double.parseDouble(precioTexto);

            if (precio < 0) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Precio incorrecto",
                        "El precio no puede ser negativo."
                );

                return;
            }

            Artesania artesania = new Artesania(
                    codigo,
                    nombre,
                    tipo,
                    precio,
                    rutaImagen
            );

            listado.agregar(artesania);

            tbProductos.getItems().setAll(
                    listado.obtenerRegistros()
            );

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Producto guardado",
                    "La artesanía fue guardada correctamente."
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Precio incorrecto",
                    "El precio debe ser un valor numérico."
            );
        }
    }

    @FXML
    private void buscarProducto(ActionEvent event) {

        String codigo = txtBuscar.getText().trim();

        if (codigo.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Código requerido",
                    "Ingrese el código de la artesanía."
            );

            return;
        }

        Artesania artesania =
                listado.buscarPorCodigo(codigo);

        if (artesania == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Producto no encontrado",
                    "No existe una artesanía con ese código."
            );

            return;
        }

        tbProductos.getSelectionModel().select(artesania);
        tbProductos.scrollTo(artesania);

        if (artesania.getRutaImagen() != null) {

            File archivo =
                    new File(artesania.getRutaImagen());

            if (archivo.exists()) {

                imgPrevia.setImage(
                        new Image(archivo.toURI().toString())
                );
            }
        }

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Artesanía encontrada",
                "Código: " + artesania.getCodigo()
                        + "\nNombre: " + artesania.getNombre()
                        + "\nTipo: " + artesania.getTipo()
                        + "\nPrecio: C$ "
                        + String.format("%.2f", artesania.getPrecio())
        );
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();

        cbTipo.getSelectionModel().clearSelection();

        imgPrevia.setImage(null);

        rutaImagen = null;

        txtCodigo.requestFocus();
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