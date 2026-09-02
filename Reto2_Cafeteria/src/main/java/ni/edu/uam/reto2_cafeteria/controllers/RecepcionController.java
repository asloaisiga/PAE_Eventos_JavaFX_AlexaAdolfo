package ni.edu.uam.reto2_cafeteria.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ni.edu.uam.reto2_cafeteria.dao.CafeDao;
import ni.edu.uam.reto2_cafeteria.modelos.Cafe;

import java.util.Optional;

public class RecepcionController {

    @FXML
    private TableView<Cafe> tbLotes;

    @FXML
    private TableColumn<Cafe, String> colProductor;

    @FXML
    private TableColumn<Cafe, String> colLote;

    @FXML
    private TableColumn<Cafe, Double> colCantidad;

    @FXML
    private Label lblProductor;

    @FXML
    private Label lblLote;

    @FXML
    private Label lblCantidad;

    private final CafeDao cafeDao = new CafeDao();

    @FXML
    public void initialize() {

        colProductor.setCellValueFactory(
                new PropertyValueFactory<>("productor")
        );

        colLote.setCellValueFactory(
                new PropertyValueFactory<>("lote")
        );

        colCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidad")
        );

        tbLotes.setItems(cafeDao.obtenerLotes());

        tbLotes.setPlaceholder(
                new Label("No hay lotes registrados")
        );
    }

    @FXML
    private void agregarLote(ActionEvent event) {

        Optional<Cafe> resultado = mostrarFormulario("Agregar lote", null);

        if (resultado.isPresent()) {

            Cafe cafe = resultado.get();

            cafeDao.agregar(cafe);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Lote agregado", "El lote fue registrado correctamente.");
        }
    }

    @FXML
    private void mostrarDetalles(MouseEvent event) {

        Cafe cafeSeleccionado =
                tbLotes.getSelectionModel().getSelectedItem();

        if (cafeSeleccionado != null) {

            lblProductor.setText(
                    cafeSeleccionado.getProductor()
            );

            lblLote.setText(
                    cafeSeleccionado.getLote()
            );

            lblCantidad.setText(
                    String.valueOf(cafeSeleccionado.getCantidad())
            );
        }
    }

    @FXML
    private void editarLote(ActionEvent event) {

        Cafe cafeSeleccionado =
                tbLotes.getSelectionModel().getSelectedItem();

        if (cafeSeleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Sin selección",
                    "Seleccione un lote para editar."
            );

            return;
        }

        Optional<Cafe> resultado = mostrarFormulario(
                "Editar lote",
                cafeSeleccionado
        );

        if (resultado.isPresent()) {

            Cafe cafeNuevo = resultado.get();

            cafeDao.actualizar(
                    cafeSeleccionado,
                    cafeNuevo
            );

            tbLotes.refresh();

            mostrarDetallesSeleccionado(cafeNuevo);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Lote actualizado",
                    "El lote fue actualizado correctamente."
            );
        }
    }

    @FXML
    private void eliminarLote(ActionEvent event) {

        Cafe cafeSeleccionado =
                tbLotes.getSelectionModel().getSelectedItem();

        if (cafeSeleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Sin selección",
                    "Seleccione un lote para eliminar."
            );

            return;
        }

        Alert confirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Confirmar eliminación");

        confirmacion.setHeaderText(
                "¿Desea eliminar este lote?"
        );

        confirmacion.setContentText(
                "Productor: "
                        + cafeSeleccionado.getProductor()
                        + "\nLote: "
                        + cafeSeleccionado.getLote()
        );

        Optional<ButtonType> respuesta =
                confirmacion.showAndWait();

        if (respuesta.isPresent()
                && respuesta.get() == ButtonType.OK) {

            cafeDao.eliminar(cafeSeleccionado);

            limpiarDetalles();

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Lote eliminado",
                    "El lote fue eliminado correctamente."
            );
        }
    }

    private Optional<Cafe> mostrarFormulario(String titulo, Cafe cafeEditar) {
        while (true) {

            Dialog<ButtonType> dialog = new Dialog<>();

            dialog.setTitle(titulo);

            dialog.setHeaderText("Ingrese los datos del lote de café");

            TextField txtProductor = new TextField();

            TextField txtLote = new TextField();

            TextField txtCantidad = new TextField();

            txtProductor.setPromptText("Nombre del productor");

            txtLote.setPromptText("Código o nombre del lote");

            txtCantidad.setPromptText("Cantidad");

            if (cafeEditar != null) {
                txtProductor.setText(cafeEditar.getProductor());
                txtLote.setText(cafeEditar.getLote());
                txtCantidad.setText(String.valueOf(cafeEditar.getCantidad()));
            }

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            grid.setPadding(new Insets(20, 20, 10, 20));

            grid.add(new Label("Productor:"), 0, 0);

            grid.add(txtProductor, 1, 0);

            grid.add(new Label("Lote:"), 0, 1);

            grid.add(txtLote, 1, 1);

            grid.add(new Label("Cantidad:"), 0, 2);

            grid.add(txtCantidad, 1, 2);

            dialog.getDialogPane()
                    .setContent(grid);

            ButtonType btnGuardar =
                    new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);

            dialog.getDialogPane().getButtonTypes().addAll(btnGuardar, ButtonType.CANCEL);

            Optional<ButtonType> resultado = dialog.showAndWait();

            if (resultado.isEmpty() || resultado.get() == ButtonType.CANCEL) {
                return Optional.empty();
            }

            String productor =
                    txtProductor.getText().trim();

            String lote =
                    txtLote.getText().trim();

            String cantidadTexto =
                    txtCantidad.getText()
                            .trim()
                            .replace(",", ".");

            if (productor.isEmpty()
                    || lote.isEmpty()
                    || cantidadTexto.isEmpty()) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Campos incompletos",
                        "Todos los campos son obligatorios."
                );

                continue;
            }

            try {

                double cantidad =
                        Double.parseDouble(cantidadTexto);

                if (cantidad <= 0) {

                    mostrarAlerta(
                            Alert.AlertType.WARNING, "Cantidad incorrecta", "La cantidad debe ser mayor que cero.");
                    continue;
                }

                Cafe cafe = new Cafe(productor, lote, cantidad);

                return Optional.of(cafe);

            } catch (NumberFormatException e) {

                mostrarAlerta(
                        Alert.AlertType.ERROR, "Cantidad incorrecta", "La cantidad debe ser un número.");
            }
        }
    }

    private void mostrarDetallesSeleccionado(
            Cafe cafe
    ) {

        lblProductor.setText(
                cafe.getProductor()
        );

        lblLote.setText(
                cafe.getLote()
        );

        lblCantidad.setText(
                String.valueOf(
                        cafe.getCantidad()
                )
        );
    }

    private void limpiarDetalles() {

        lblProductor.setText("-");
        lblLote.setText("-");
        lblCantidad.setText("-");
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje
    ) {

        Alert alerta =
                new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}