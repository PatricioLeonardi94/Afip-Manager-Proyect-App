package com.company.java.model.Database;

import com.company.java.model.Excel.excelManagment;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.poi.ss.usermodel.Row;

public class Resumen implements excelManagment {

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty clienteId;
    private SimpleIntegerProperty año;
    private SimpleStringProperty mes;
    private SimpleIntegerProperty punto_venta;
    private SimpleDoubleProperty gravado;
    private SimpleDoubleProperty iva;
    private SimpleDoubleProperty total;

    public Resumen() {
        this.id = new SimpleIntegerProperty();
        this.clienteId = new SimpleIntegerProperty();
        this.año = new SimpleIntegerProperty();
        this.mes = new SimpleStringProperty();
        this.punto_venta = new SimpleIntegerProperty();
        this.gravado = new SimpleDoubleProperty();
        this.iva = new SimpleDoubleProperty();
        this.total = new SimpleDoubleProperty();
    }

    public Resumen(String input){

        this.id = new SimpleIntegerProperty();
        this.clienteId = new SimpleIntegerProperty();
        this.año = new SimpleIntegerProperty();
        this.mes = new SimpleStringProperty();
        this.punto_venta = new SimpleIntegerProperty();


        String enteroTotalAbonado = input.substring(78,91);
        String decimalTotalAbonado = input.substring(91,93);
        String constructorTotal = enteroTotalAbonado + "." + decimalTotalAbonado;
        total = new SimpleDoubleProperty(Double.parseDouble(constructorTotal));

        String enteroGrabadoAbonado = input.substring(108,121);
        String decimalGrabadoAbonado = input.substring(121,123);
        String constructorGrabado = enteroGrabadoAbonado + "." + decimalGrabadoAbonado;
        gravado = new SimpleDoubleProperty(Double.parseDouble(constructorGrabado));

        String enteroIvaAbonado = input.substring(123,136);
        String decimalIvaAbonado = input.substring(136,138);
        String constructorIva = enteroIvaAbonado + "." + decimalIvaAbonado;
        iva = new SimpleDoubleProperty(Double.parseDouble(constructorIva));
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

    public int getClienteId() {
        return clienteId.get();
    }

    public SimpleIntegerProperty clienteIdProperty() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId.set(clienteId);
    }

    public int getAño() {
        return año.get();
    }

    public SimpleIntegerProperty añoProperty() {
        return año;
    }

    public void setAño(int año) {
        this.año.set(año);
    }

    public String getMes() {
        return mes.get();
    }

    public SimpleStringProperty mesProperty() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes.set(mes);
    }

    public int getPunto_venta() {
        return punto_venta.get();
    }

    public SimpleIntegerProperty punto_ventaProperty() {
        return punto_venta;
    }

    public void setPunto_venta(int punto_venta) {
        this.punto_venta.set(punto_venta);
    }

    public double getGravado() {
        return gravado.get();
    }

    public SimpleDoubleProperty gravadoProperty() {
        return gravado;
    }

    public void setGravado(double gravado) {
        this.gravado.set(gravado);
    }

    public double getIva() {
        return iva.get();
    }

    public SimpleDoubleProperty ivaProperty() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva.set(iva);
    }

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    @Override
    public void toExcel(Row row){
        row.createCell(0).setCellValue("Año");
        row.createCell(1).setCellValue(this.getAño());
        row.createCell(2).setCellValue("Mes");
        row.createCell(3).setCellValue(this.getMes());
        row.createCell(4).setCellValue("Punto de Venta");
        row.createCell(5).setCellValue(this.getPunto_venta());
    }

    public void toExcelBottom(Row row){
        row.createCell(8).setCellValue("Totales");
        row.createCell(9).setCellValue(this.getGravado());
        row.createCell(10).setCellValue(this.getIva());
        row.createCell(11).setCellValue(this.getTotal());
    }

    public void toExcelFacturaTitles(Row row){
        row.createCell(0).setCellValue("Fecha");
        row.createCell(1).setCellValue("Punto de Venta");
        row.createCell(2).setCellValue("Tipo de Factura");
        row.createCell(3).setCellValue("Numero de Factura");
        row.createCell(4).setCellValue("Razon Social");
        row.createCell(5).setCellValue("CUIT/DNI");
        row.createCell(9).setCellValue("Importe Grabados");
        row.createCell(10).setCellValue("IVA 21%");
        row.createCell(11).setCellValue("Monto Total Abonado");
    }

}
