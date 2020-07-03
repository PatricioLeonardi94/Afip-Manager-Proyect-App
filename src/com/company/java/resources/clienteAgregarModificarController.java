package com.company.java.resources;

import com.company.java.styles.fxmlStyler;
import com.company.java.model.Database.Cliente;
import com.company.java.model.Datasource;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class clienteAgregarModificarController implements Initializable {

    @FXML
    private TableView resumenCliente;

    @FXML
    private Button volverButton;

    @FXML
    private Label FirstName;

    @FXML private TextField nombreCliente;
    @FXML private TextField cuitCliente;


    private Cliente cliente;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadCliente(Cliente cliente){
        this.cliente = cliente;
        nombreCliente.setText(cliente.getNombre());
        cuitCliente.setText(cliente.getCuit());
    }

    public void agregarCliente(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Agregar a " + nombreCliente.getText() + " como Cliente?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    return Datasource.getInstance().insertCliente(nombreCliente.getText(), cuitCliente.getText());
                }

            };
            new Thread(task).start();
            VolverToMain(actionEvent);
        }
    }

    public void modificarCliente(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Modificar el cliente " + nombreCliente.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().updateCliente(cliente.getId(), nombreCliente.getText(), cuitCliente.getText());
                }

            };
            new Thread(task).start();
            VolverToMain(actionEvent);
        }
    }

    //Volver
    public void VolverToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(mainController.class.getResource("main.fxml"));

        Parent root = loader.load();

        mainController mainController = loader.getController();
        mainController.listClientes();

        Scene resumenForCliente = new Scene(root, 800, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resumenForCliente);
        fxmlStyler.getInstance().centerStage(window, 800, 600);
        window.show();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
