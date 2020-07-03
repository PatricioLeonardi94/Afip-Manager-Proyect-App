package com.company.java.resources;

import com.company.java.styles.fxmlStyler;
import com.company.java.model.Database.Cliente;
import com.company.java.model.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class mainController implements Initializable {
    @FXML
    private TableView clienteTable;

    @FXML
    private Button mostrarResumenes;

    @FXML
    private ProgressBar progressBar;



    @FXML
    public void listClientes() {
        Task<ObservableList<Cliente>> task = new GetAllCientesTask();
        clienteTable.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());

        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));

        new Thread(task).start();
    }

    @FXML
    public void listResumenForCliente(ActionEvent event) throws IOException {
        final Cliente cliente = (Cliente) clienteTable.getSelectionModel().getSelectedItem();
        if(cliente == null) {
            System.out.println("NO SELECCIONO CLIENTE");
            return;
        }

        FXMLLoader loader = new FXMLLoader(resumenController.class.getResource("resumen.fxml"));
        Parent root = loader.load();

        resumenController controller = loader.<resumenController>getController();
        controller.populateList(cliente);


        Scene resumenForCliente = new Scene(root, 1200, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resumenForCliente);
        fxmlStyler.getInstance().centerStage(window, 1200, 600);
        window.show();
    }

    @FXML
    public void agregarCliente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(clienteAgregarModificarController.class.getResource("clienteAgregarModificar.fxml"));
        Parent root = loader.load();


        Scene resumenForCliente = new Scene(root, 800, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resumenForCliente);
        fxmlStyler.getInstance().centerStage(window, 800, 600);
        window.show();
    }

    @FXML
    public void updateCliente(ActionEvent event) throws IOException {
        Cliente cliente = (Cliente) clienteTable.getSelectionModel().getSelectedItem();

        if(cliente != null){
            FXMLLoader loader = new FXMLLoader(clienteAgregarModificarController.class.getResource("clienteAgregarModificar.fxml"));
            Parent root = loader.load();

            clienteAgregarModificarController controller = loader.<clienteAgregarModificarController>getController();
            controller.loadCliente(cliente);

            Scene resumenForCliente = new Scene(root, 800, 600);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(resumenForCliente);
            fxmlStyler.getInstance().centerStage(window, 800, 600);
            window.show();
        }
    }

    @FXML
    public void deleteCliente(ActionEvent actionEvent) {
        Cliente cliente = (Cliente) clienteTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Desea eliminar el cliente " + cliente.getNombre()+ ", esto eliminara las facturas y resumenes asociados?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Usted esta por elminar el cliente " + cliente.getNombre());
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Task<Boolean> task = new Task<>() {
                @Override
                protected Boolean call() throws Exception {
                    return Datasource.getInstance().deleteClienteById(cliente.getId());
                }
            };
            task.setOnSucceeded(e -> {
                Alert alertSuc = new Alert(Alert.AlertType.INFORMATION);
                alertSuc.setTitle("Informe de la operacion");
                alertSuc.setHeaderText("Se a eliminado el cliente " + cliente.getNombre() + " con exito");
                alertSuc.showAndWait();
            });
            new Thread(task).start();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}


class GetAllCientesTask extends Task {

    @Override
    public ObservableList<Cliente> call()  {
        return FXCollections.observableArrayList
                (Datasource.getInstance().queryClientes(Datasource.ORDER_BY_ASC));
    }
}


