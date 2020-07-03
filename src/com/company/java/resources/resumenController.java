package com.company.java.resources;

import com.company.java.styles.fxmlStyler;
import com.company.java.model.Database.Cliente;
import com.company.java.model.Database.Resumen;
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


public class resumenController implements Initializable {

    @FXML
    private TableView resumenCliente;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button volverButton;

    @FXML
    private Button agregarFacturas;

    @FXML
    private Label nameCliente;

    private Cliente cliente;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void populateList(Cliente cliente){
        this.cliente = cliente;
        nameCliente.setText(cliente.getNombre());
        resumenesForListView();
    }

    private void resumenesForListView(){
        Task<ObservableList<Resumen>> task = new Task<ObservableList<Resumen>>() {
            @Override
            protected ObservableList<Resumen> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryResumenForClienteId(cliente.getId()));
            }
        };
        resumenCliente.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    public void ListFacturasForResumen(ActionEvent actionEvent) throws IOException {

        final Resumen resumen = (Resumen) resumenCliente.getSelectionModel().getSelectedItem();

        if(resumen != null){
            FXMLLoader loader = new FXMLLoader(facturasController.class.getResource("facturas.fxml"));

            Parent root = loader.load();

            facturasController controller = loader.<facturasController>getController();

            Scene resumenForCliente = new Scene(root, 1400, 800);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            controller.populateList(this.cliente, resumen, window);
            window.setScene(resumenForCliente);
            fxmlStyler.getInstance().centerStage(window, 1400, 800);
            window.show();
        }
    }


    //Ir a Agregar Facturas
    public void AgregarFacturasCliente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(agregarFacturasController.class.getResource("agregarFacturas.fxml"));

        Parent root = loader.load();

        agregarFacturasController controller = loader.<agregarFacturasController>getController();


        Scene resumenForCliente = new Scene(root, 800, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controller.load(this.cliente, window);
        window.setScene(resumenForCliente);
        fxmlStyler.getInstance().centerStage(window, 800, 600);
        window.show();
    }

    public void DeleteResumenCliente(ActionEvent actionEvent) {

        final Resumen resumen = (Resumen) resumenCliente.getSelectionModel().getSelectedItem();

        if(resumen != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Desea eliminar el Resumen del año: " + resumen.getAño() + " mes: " + resumen.getMes() +
                    "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return Datasource.getInstance().deleteResumenByResumenId(resumen.getId());
                    }
                };

                task.setOnSucceeded(e -> {
                    Alert alertSuc = new Alert(Alert.AlertType.INFORMATION);
                    alertSuc.setTitle("Informe de la operacion");
                    alertSuc.setHeaderText("La operacion eliminar se realizo con exito");
                    alertSuc.showAndWait();
                });
                new Thread(task).start();
                resumenesForListView();
            }
        }
    }


    //Volver a clientes
    public void VolverToClientes(ActionEvent event) throws IOException {
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
