package com.company.java.model.Afip;
import com.company.java.model.Database.Factura;
import com.company.java.model.Database.Resumen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class afipFileReader {

    private File file;
    private Resumen resumen;
    private List<Factura> facturas = new ArrayList<>();

    public void setFile(File file) {
        this.file = file;
        listFacturas();
    }


    public void listFacturas() {

        try (BufferedReader dirFile = new BufferedReader(new FileReader(this.file))) {
            String input;
            while ((input = dirFile.readLine()) != null) {
                String dateString = input.substring(1, 9);

                if (input.substring(0,1).contains("1")) {
                    Factura facturaGeneric = new Factura(input);
                    facturas.add(facturaGeneric);
                }
                if(input.substring(0,1).contains("2")){
                    this.resumen = new Resumen(input);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Resumen getResumen() {
        return resumen;
    }

    public void setResumen(Resumen resumen) {
        this.resumen = resumen;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }



}

