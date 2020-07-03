package com.company.java.model.Excel;

import com.company.java.model.Database.Cliente;
import com.company.java.model.Database.Resumen;
import com.company.java.model.Database.Factura;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelManagerForFacturas {
    private Cliente cliente;
    private Resumen resumen;
    private  Object[] facturas;
    private String file;

//  La implementacion de una Observable List de facturas no deja la reutilizacion de datos de manera directa,
//  solo la obtencion de los mismo a travez de un casteo explicito a la clase
    public ExcelManagerForFacturas(Cliente cliente, Resumen resumen, Object[] factura_generics, String file){
        this.cliente = cliente;
        this.resumen = resumen;
        this.facturas = factura_generics;
        this.file = file;

        try{
            this.generateExcel();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void generateExcel() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(this.resumen.getYear() + this.resumen.getMonth());

        Row headerRow = sheet.createRow(0);
        this.cliente.toExcel(headerRow);

        Row resumenHeader = sheet.createRow(2);
        this.resumen.toExcel(resumenHeader);

        Row titlesRowForFacturas = sheet.createRow(4);
        this.resumen.toExcelFacturaTitles(titlesRowForFacturas);

        int rowNumberForFacturas = 5;

//        La manera de realizarlo de manera directa sin necesidad de casteo explicito de clase
//        for (Factura_Generic factura: this.facturas) {
//            Row row = sheet.createRow(rowNumberForFacturas);
//            factura.toExcel(row);
//            rowNumberForFacturas++;
//        }

        for(int i=0; i<this.facturas.length; i++){
            Row row = sheet.createRow(rowNumberForFacturas);
            ((Factura)facturas[i]).toExcel(row);
            rowNumberForFacturas++;
        }

        Row rowBottomResume = sheet.createRow(rowNumberForFacturas);
        this.resumen.toExcelBottom(rowBottomResume);

        for(int i = 0; i < 12; i++ ){
            sheet.autoSizeColumn(i);
        }


        try (FileOutputStream outputStream = new FileOutputStream(this.file + ".xlsx")) {
            workbook.write(outputStream);
            workbook.close();
        }
    }

}
