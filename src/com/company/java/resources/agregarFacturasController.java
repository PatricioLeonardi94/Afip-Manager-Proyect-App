package com.company.java.resources;

import com.company.java.styles.fxmlStyler;
import com.company.java.model.Database.Cliente;
import com.company.java.model.Datasource;
import com.company.java.model.Afip.afipFileReader;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class agregarFacturasController implements Initializable {

    @FXML
    private TableView resumenCliente;

    @FXML
    private Button volverButton;

    @FXML
    private Label nameCliente;

    @FXML
    private ProgressBar progressBar;


    private Cliente cliente;
    private Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void load(Cliente cliente, Stage stage){
        this.cliente = cliente;
        nameCliente.setText(cliente.getNombre());
        this.stage = stage;
    }

    public void agregarArchivo(ActionEvent actionEvent) {
        FileChooser directoryChooser = new FileChooser();
        directoryChooser.setInitialDirectory(new File("C:\\Users\\Patricio\\Desktop"));
        File selectedDirectory = directoryChooser.showOpenDialog(this.stage);

        afipFileReader afipFileReader = new afipFileReader();
        afipFileReader.setFile(selectedDirectory);


        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return Datasource.getInstance().insertManyFactura(cliente.getNombre(), cliente.getCuit(), afipFileReader.getResumen(), afipFileReader.getFacturas());
            }
        };
        progressBar.progressProperty().bind(task.progressProperty());
        progressBar.setVisible(true);


        task.setOnSucceeded(e -> {
            progressBar.setVisible(false);
            Alert alertSuc = new Alert(Alert.AlertType.INFORMATION);
            alertSuc.setTitle("Informe de la operacion");
            alertSuc.setHeaderText("Se han agregado las facturas con exito");
            alertSuc.showAndWait();
        });
        task.setOnFailed(e -> {
            progressBar.setVisible(false);
            Alert alertSuc = new Alert(Alert.AlertType.INFORMATION);
            alertSuc.setTitle("Informe de la operacion");
            alertSuc.setHeaderText("Ha ocurrido un problema al agregar las facturas");
            alertSuc.showAndWait();
        });

        new Thread(task).start();
    }


    //Volver
    public void VolverToResumenes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(resumenController.class.getResource("resumen.fxml"));

        Parent root = loader.load();

        resumenController controller = loader.<resumenController>getController();
        controller.populateList(cliente);

        Scene resumenForCliente = new Scene(root, 800, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(resumenForCliente);
        fxmlStyler.getInstance().centerStage(window, 800, 600);
        window.show();
    }
}
