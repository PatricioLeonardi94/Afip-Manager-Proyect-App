package com.company.java.resources;

import com.company.java.styles.fxmlStyler;
import com.company.java.model.Database.Cliente;
import com.company.java.model.Excel.ExcelManagerForFacturas;
import com.company.java.model.Database.Resumen;
import com.company.java.model.Datasource;
import com.company.java.model.Database.Factura;
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


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;


public class facturasController implements Initializable {

    @FXML
    private TableView facturasResumen;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button volverButton;

    @FXML
    private Button agregarFacturas;

    @FXML
    private Label nameCliente;

    @FXML
    private Label impGrabadoCol;

    @FXML
    private Label ivaCol;

    @FXML
    private Label totalCol;

    private Cliente cliente;
    private Resumen resumen;
    private Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void populateList(Cliente cliente, Resumen resumen, Stage stage){
        this.cliente = cliente;
        this.resumen = resumen;
        this.stage = stage;


        Double importGrabado = this.resumen.getGravado();
        impGrabadoCol.setText("$" + importGrabado.toString());
        Double iva = this.resumen.getIva();
        ivaCol.setText("$" +iva.toString());
        Double total = this.resumen.getTotal();
        totalCol.setText("$" +total.toString());

        nameCliente.setText(cliente.getNombre());
        Task<ObservableList<Factura>> task = new Task<ObservableList<Factura>>() {
            @Override
            protected ObservableList<Factura> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryFacturasForResumenId(resumen.getId()));
            }
        };
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);

        task.setOnSucceeded(e -> progressBar.setVisible(false));
        task.setOnFailed(e -> progressBar.setVisible(false));
        facturasResumen.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();

    }

    public void ToExcel(ActionEvent actionEvent) throws ExecutionException, InterruptedException, IOException {

        JFileChooser c = new JFileChooser();
        c.setCurrentDirectory(new File("C:\\Users\\Patricio\\Desktop"));

        int rVal = c.showSaveDialog(null);
        if (rVal == JFileChooser.APPROVE_OPTION) {

            String fileName = c.getSelectedFile().getAbsolutePath();

            if(c.getSelectedFile().getName().isEmpty()){
                fileName = fileName + this.cliente.getNombre() + this.resumen.getAño() + this.resumen.getMes();
            }

//
            Task<ObservableList<Factura>> task = new Task<ObservableList<Factura>>() {
                @Override
                protected ObservableList<Factura> call() throws Exception {
                    return FXCollections.observableArrayList(
                            Datasource.getInstance().queryFacturasForResumenId(resumen.getId()));
                }
            };
            task.setOnSucceeded(e -> {
                Alert alertSuc = new Alert(Alert.AlertType.INFORMATION);
                alertSuc.setTitle("Informe de la operacion");
                alertSuc.setHeaderText("Se ha generado el archivo de Excel correctamente");
                alertSuc.showAndWait();
            });
            new Thread(task).start();

            Object[] facturas =  task.get().toArray();

            new ExcelManagerForFacturas(this.cliente, this.resumen, facturas, fileName);
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {

        }

    }

    public void VolverToClientes(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(resumenController.class.getResource("resumen.fxml"));

        Parent root = loader.load();

        resumenController controller = loader.<resumenController>getController();
        controller.populateList(cliente);

        Scene resumenForCliente = new Scene(root, 800, 600);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(resumenForCliente);
        fxmlStyler.getInstance().centerStage(window, 800, 600);
        window.show();
    }
}
