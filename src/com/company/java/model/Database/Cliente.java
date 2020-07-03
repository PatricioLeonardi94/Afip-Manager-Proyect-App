package com.company.java.model.Database;

import com.company.java.model.Excel.excelManagment;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.poi.ss.usermodel.Row;

public class Cliente implements excelManagment {

 private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty cuit;

    public Cliente() {
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.cuit = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getCuit() {
        return cuit.get();
    }

    public SimpleStringProperty cuitProperty() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit.set(cuit);
    }

    @Override
    public void toExcel(Row row) {
        row.createCell(0).setCellValue("Nombre");
        row.createCell(1).setCellValue(this.getNombre());
        row.createCell(2).setCellValue("CUIT");
        row.createCell(3).setCellValue(this.getCuit());
    }
}
