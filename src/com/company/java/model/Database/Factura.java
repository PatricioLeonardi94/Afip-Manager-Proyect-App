package com.company.java.model.Database;

import com.company.java.model.Excel.excelManagment;
import javafx.beans.property.*;
import org.apache.poi.ss.usermodel.Row;

public class Factura implements excelManagment {

    private SimpleIntegerProperty cantidadDeHojas;

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty resumenId;
    private SimpleStringProperty fecha;
    private SimpleIntegerProperty puntoDeVenta;
    private SimpleStringProperty tipoFactura;
    private SimpleIntegerProperty numeroFactura;
    private SimpleIntegerProperty numeroComprobanteRegistrado;
    private SimpleStringProperty razonSocial;
    private SimpleStringProperty cuitDni;
    private SimpleDoubleProperty importeGrabados;
    private SimpleDoubleProperty iva;
    private SimpleDoubleProperty montoTotalAbonado;


    public Factura(){
       this.id = new SimpleIntegerProperty();
       this.resumenId = new SimpleIntegerProperty();
       this.fecha = new SimpleStringProperty();
       this.puntoDeVenta = new SimpleIntegerProperty();
       this.tipoFactura = new SimpleStringProperty();
       this.numeroFactura = new SimpleIntegerProperty();
       this.numeroComprobanteRegistrado = new SimpleIntegerProperty();
       this.razonSocial = new SimpleStringProperty();
       this.cuitDni = new SimpleStringProperty();
       this.importeGrabados = new SimpleDoubleProperty();
       this.iva = new SimpleDoubleProperty();
       this.montoTotalAbonado = new SimpleDoubleProperty();
    }

    public Factura(String input){

        String dateString = input.substring(1,9);

        fecha = new SimpleStringProperty(dateString);

        puntoDeVenta = new SimpleIntegerProperty(Integer.parseInt(input.substring(12,16)));
        numeroFactura = new SimpleIntegerProperty(Integer.parseInt(input.substring(16,24)));
        numeroComprobanteRegistrado = new SimpleIntegerProperty(Integer.parseInt(input.substring(24,32)));
        cantidadDeHojas = new SimpleIntegerProperty(Integer.parseInt(input.substring(32,35)));

        cuitDni = new SimpleStringProperty(input.substring(37,48));
        razonSocial = new SimpleStringProperty(input.substring(48, 78));


        String enteroTotalAbonado = input.substring(78,91);
        String decimalTotalAbonado = input.substring(91,93);
        String constructorTotal = enteroTotalAbonado + "." + decimalTotalAbonado;
        montoTotalAbonado = new SimpleDoubleProperty(Double.parseDouble(constructorTotal));

        String enteroGrabadoAbonado = input.substring(108,121);
        String decimalGrabadoAbonado = input.substring(121,123);
        String constructorGrabado = enteroGrabadoAbonado + "." + decimalGrabadoAbonado;
        importeGrabados = new SimpleDoubleProperty(Double.parseDouble(constructorGrabado));

        String enteroIvaAbonado = input.substring(123,136);
        String decimalIvaAbonado = input.substring(136,138);
        String constructorIva = enteroIvaAbonado + "." + decimalIvaAbonado;
        iva = new SimpleDoubleProperty(Double.parseDouble(constructorIva));

        String tipoFacturaContainer = (input.substring(9,11));

        //Factura A
        if(tipoFacturaContainer.contains("01")) {
            tipoFactura = new SimpleStringProperty("Factura A");
        }
        //Nota de Credito A
        if(tipoFacturaContainer.contains("03")) {
            tipoFactura = new SimpleStringProperty("Nota de Credito A");
            montoTotalAbonado = new SimpleDoubleProperty(Double.parseDouble(constructorTotal ) * (-1));
            importeGrabados = new SimpleDoubleProperty(Double.parseDouble(constructorGrabado ) * (-1));
            iva = new SimpleDoubleProperty(Double.parseDouble(constructorIva ) * (-1));
        }
        //Factura B
        if(tipoFacturaContainer.contains("06")) {
            tipoFactura = new SimpleStringProperty("Factura B");
        }
        //Factura C
        if(tipoFacturaContainer.contains("11")) {
            tipoFactura = new SimpleStringProperty("Factura C");
        }
        //Nota de Credito C
        if(tipoFacturaContainer.contains("13")) {
            tipoFactura = new SimpleStringProperty("Nota de Credito C");
            montoTotalAbonado = new SimpleDoubleProperty(Double.parseDouble(constructorTotal ) * (-1));
            importeGrabados = new SimpleDoubleProperty(Double.parseDouble(constructorGrabado ) * (-1));
            iva = new SimpleDoubleProperty(Double.parseDouble(constructorIva ) * (-1));
        }
        //Recibo C
        if(tipoFacturaContainer.contains("15")) {
            tipoFactura = new SimpleStringProperty("Recibo C");
        }
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

    public int getResumenId() {
        return resumenId.get();
    }

    public SimpleIntegerProperty resumenIdProperty() {
        return resumenId;
    }

    public void setResumenId(int resumenId) {
        this.resumenId.set(resumenId);
    }

    public String getFecha() {
        return fecha.get();
    }

    public SimpleStringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public int getPuntoDeVenta() {
        return puntoDeVenta.get();
    }

    public SimpleIntegerProperty puntoDeVentaProperty() {
        return puntoDeVenta;
    }

    public void setPuntoDeVenta(int puntoDeVenta) {
        this.puntoDeVenta.set(puntoDeVenta);
    }

    public String getTipoFactura() {
        return tipoFactura.get();
    }

    public SimpleStringProperty tipoFacturaProperty() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura.set(tipoFactura);
    }

    public int getNumeroFactura() {
        return numeroFactura.get();
    }

    public SimpleIntegerProperty numeroFacturaProperty() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura.set(numeroFactura);
    }

    public int getNumeroComprobanteRegistrado() {
        return numeroComprobanteRegistrado.get();
    }

    public SimpleIntegerProperty numeroComprobanteRegistradoProperty() {
        return numeroComprobanteRegistrado;
    }

    public void setNumeroComprobanteRegistrado(int numeroComprobanteRegistrado) {
        this.numeroComprobanteRegistrado.set(numeroComprobanteRegistrado);
    }

    public String getRazonSocial() {
        return razonSocial.get();
    }

    public SimpleStringProperty razonSocialProperty() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial.set(razonSocial);
    }

    public String getCuitDni() {
        return cuitDni.get();
    }

    public SimpleStringProperty cuitDniProperty() {
        return cuitDni;
    }

    public void setCuitDni(String cuitDni) {
        this.cuitDni.set(cuitDni);
    }

    public double getImporteGrabados() {
        return importeGrabados.get();
    }

    public SimpleDoubleProperty importeGrabadosProperty() {
        return importeGrabados;
    }

    public void setImporteGrabados(double importeGrabados) {
        this.importeGrabados.set(importeGrabados);
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

    public double getMontoTotalAbonado() {
        return montoTotalAbonado.get();
    }

    public SimpleDoubleProperty montoTotalAbonadoProperty() {
        return montoTotalAbonado;
    }

    public void setMontoTotalAbonado(double montoTotalAbonado) {
        this.montoTotalAbonado.set(montoTotalAbonado);
    }

    private String fechaToExcelFormat(String fecha){
        String fechaForExcel = this.getFecha();
        String a = fechaForExcel.substring(0,4);
        String m = fechaForExcel.substring(4,6);
        String d = fechaForExcel.substring(6,8);
        String  res = d + "/" + m + "/" + a;
        return res;
    }

    @Override
    public void toExcel(Row row){
        row.createCell(0).setCellValue(fechaToExcelFormat(this.getFecha()));
        row.createCell(1).setCellValue(this.getPuntoDeVenta());
        row.createCell(2).setCellValue(this.getTipoFactura());
        row.createCell(3).setCellValue(this.getNumeroFactura());
        row.createCell(4).setCellValue(this.getRazonSocial());
        row.createCell(5).setCellValue(this.getCuitDni());
        row.createCell(9).setCellValue(this.getImporteGrabados());
        row.createCell(10).setCellValue(this.getIva());
        row.createCell(11).setCellValue(this.getMontoTotalAbonado());
    }
}

