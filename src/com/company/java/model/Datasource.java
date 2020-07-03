package com.company.java.model;

import com.company.java.model.Database.Cliente;
import com.company.java.model.Database.Factura;
import com.company.java.model.Database.Resumen;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Datasource {
    //Seleccionado particularmente para mi propia base de datos, de momento local.

    public static final String DB_NAME = "Afip.db";

    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\databases\\" + DB_NAME;

    public static final String TABLE_CLIENTES = "clientes";
    public static final String COLUMN_CLIENTE_ID = "_id";
    public static final String COLUMN_CLIENTE_NOMBRE = "nombre";
    public static final String COLUMN_CLIENTE_CUIT = "cuit";
    public static final int INDEX_CLIENTE_ID = 1;
    public static final int INDEX_CLIENTE_NOMBRE = 2;
    public static final int INDEX_CLIENTE_CUIT = 3;

    public static final String TABLE_RESUMEN = "resumen";
    public static final String COLUMN_RESUMEN_ID = "_id";
    public static final String COLUMN_RESUMEN_CLIENTE = "cliente";
    public static final String COLUMN_RESUMEN_AÑO = "año";
    public static final String COLUMN_RESUMEN_MES = "mes";
    public static final String COLUMN_RESUMEN_PUNTO_DE_VENTA = "punto_venta";
    public static final String COLUMN_RESUMEN_IMPORTE_GRAVADO = "gravado";
    public static final String COLUMN_RESUMENL_IVA = "iva";
    public static final String COLUMN_RESUMEN_TOTAL = "total";
    public static final int INDEX_RESUMEN_ID = 1;
    public static final int INDEX_RESUMEN_CLIENTE = 2;
    public static final int INDEX_RESUMEN_AÑO = 3;
    public static final int INDEX_RESUMEN_MES = 4;
    public static final int INDEX_RESUMEN_PUNTO_DE_VENTA = 5;
    public static final int INDEX_RESUMEN_IMPORTE_GRAVADO = 6;
    public static final int INDEX_RESUMEN_IVA = 7;
    public static final int INDEX_RESUMEN_TOTAL = 8;

    public static final String TABLE_FACTURA = "factura";
    public static final String COLUMN_FACTURA_ID = "_id";
    public static final String COLUMN_FACTURA_RESUMEN_ID = "resumen_id";
    public static final String COLUMN_FACTURA_FECHA = "fecha";
    public static final String COLUMN_FACTURA_PUNTO_DE_VENTA = "punto_venta";
    public static final String COLUMN_FACTURA_TIPO_FACTURA = "tipo_factura";
    public static final String COLUMN_FACTURA_NUMERO_FACTURA = "numero";
    public static final String COLUMN_FACTURA_RAZON_SOCIAL= "razon_social";
    public static final String COLUMN_FACTURA_CUIT= "cuit";
    public static final String COLUMN_FACTURA_IMPORTE_GRAVADO = "gravado";
    public static final String COLUMN_FACTURA_IVA = "iva";
    public static final String COLUMN_FACTURA_TOTAL = "total";
    public static final int INDEX_FACTURA_ID = 1;
    public static final int INDEX_FACTURA_RESUMEN_REFERENCIA = 2;
    public static final int INDEX_FACTURA_FECHA = 3;
    public static final int INDEX_FACTURA_PUNTO_DE_VENTA = 4;
    public static final int INDEX_FACTURA_TIPO_FACTURA = 5;
    public static final int INDEXN_FACTURA_NUMERO_FACTURA = 6;
    public static final int INDEX_FACTURA_RAZON_SOCIAL= 7;
    public static final int INDEX_FACTURA_CUIT= 8;
    public static final int INDEX_FACTURA_IMPORTE_GRAVADO = 9;
    public static final int INDEX_FACTURA_IVA = 10;
    public static final int INDEX_FACTURA_TOTAL = 11;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    //Querys

    public static final String QUERY_CLIENTE = "SELECT " + COLUMN_CLIENTE_ID + " FROM " +
            TABLE_CLIENTES + " WHERE " + COLUMN_CLIENTE_NOMBRE + " = ?";

    public static final String QUERY_RESUMEN = "SELECT " + COLUMN_RESUMEN_ID + " FROM " +
            TABLE_RESUMEN + " WHERE " + COLUMN_RESUMEN_CLIENTE + " = ?" + " AND " + COLUMN_RESUMEN_AÑO + " = ?" + " AND " + COLUMN_RESUMEN_MES + " = ?";

    public static final String QUERY_FACTURA = "SELECT " + COLUMN_FACTURA_ID + " FROM " +
            TABLE_FACTURA + " WHERE " + COLUMN_FACTURA_NUMERO_FACTURA + " = ?" + " AND " + COLUMN_FACTURA_RESUMEN_ID + " = ?";

    public static final String QUERY_CLIENTES_BY_CLIENTE_NOMBRE =
            " ORDER BY " + TABLE_CLIENTES + "." + COLUMN_CLIENTE_NOMBRE + " COLLATE NOCASE ";

    public static final String QUERY_RESUMEN_BY_CLIIENTE_ID = "SELECT * FROM " + TABLE_RESUMEN +
            " WHERE " + COLUMN_RESUMEN_CLIENTE + " = ? ORDER BY " + COLUMN_RESUMEN_AÑO + ", " + COLUMN_RESUMEN_MES + " COLLATE NOCASE";

    public static final String QUERY_FACTURAS_BY_RESUMEN_ID = "SELECT * FROM " + TABLE_FACTURA +
            " WHERE " + COLUMN_FACTURA_RESUMEN_ID + " = ? ORDER BY " + COLUMN_FACTURA_NUMERO_FACTURA + " COLLATE NOCASE";

    //Inserts

    public static final String INSERT_CLIENTE = "INSERT INTO " + TABLE_CLIENTES +
            '(' + COLUMN_CLIENTE_NOMBRE + ", " + COLUMN_CLIENTE_CUIT + ") VALUES(?, ?)";

    public static final String INSERT_RESUMEN = "INSERT INTO " + TABLE_RESUMEN +
            '(' +  COLUMN_RESUMEN_CLIENTE + ", " + COLUMN_RESUMEN_AÑO + ", " + COLUMN_RESUMEN_MES +
            ", " + COLUMN_RESUMEN_PUNTO_DE_VENTA +  ", " + COLUMN_RESUMEN_IMPORTE_GRAVADO + ", " + COLUMN_RESUMENL_IVA + ", " + COLUMN_RESUMEN_TOTAL +
            ") VALUES(?, ?, ?, ?, ?, ?, ?)";

    public static final String INSERT_FACTURA = "INSERT INTO " + TABLE_FACTURA +
            '(' + COLUMN_FACTURA_RESUMEN_ID + ", " + COLUMN_FACTURA_FECHA + ", " + COLUMN_FACTURA_PUNTO_DE_VENTA +
            ", " + COLUMN_FACTURA_TIPO_FACTURA + ", " + COLUMN_FACTURA_NUMERO_FACTURA + ", " + COLUMN_FACTURA_RAZON_SOCIAL + ", " + COLUMN_FACTURA_CUIT +
            ", " + COLUMN_FACTURA_IMPORTE_GRAVADO + ", " + COLUMN_FACTURA_IVA + ", " + COLUMN_FACTURA_TOTAL +
            ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //Update

    public static final String UPDATE_CLIENTE_NOMBRE = "UPDATE " + TABLE_CLIENTES + " SET " +
            COLUMN_CLIENTE_NOMBRE + " = ?, " + COLUMN_CLIENTE_CUIT + " = ? WHERE " + COLUMN_CLIENTE_ID + " = ?";

    public static final String UPDATE_RESUMEN_TOTALES = "UPDATE " + TABLE_RESUMEN + " SET " +
            COLUMN_RESUMEN_IMPORTE_GRAVADO + " = ?, " + COLUMN_RESUMENL_IVA + " = ?, " + COLUMN_RESUMEN_TOTAL + " = ? WHERE " + COLUMN_RESUMEN_ID + " = ?";

    //Delete
//    DELETE FROM table_name WHERE condition;
    public static final String DELETE_CLIENTE = "DELETE FROM " + TABLE_CLIENTES + " WHERE " + COLUMN_CLIENTE_ID + " = ?";
    public static final String DELETE_RESUMEN = "DELETE FROM " + TABLE_RESUMEN + " WHERE " + COLUMN_RESUMEN_ID + " = ?";
    public static final String DELETE_FACTURA = "DELETE FROM " + TABLE_FACTURA + " WHERE " + COLUMN_FACTURA_RESUMEN_ID + " = ?";



    private Connection conn;

    private PreparedStatement queryClientes;
    private PreparedStatement queryResumenes;
    private PreparedStatement queryFacturas;
    private PreparedStatement queryResumenByClienteId;
    private PreparedStatement queryFacturasByResumenId;

    private PreparedStatement insertIntoCliente;
    private PreparedStatement insertIntoResumen;
    private PreparedStatement insertIntoFactura;

    private PreparedStatement updateClienteNombre;
    private PreparedStatement updateResumenTotales;

    private PreparedStatement deleteCliente;
    private PreparedStatement deleteResumen;
    private PreparedStatement deleteFactura;


    private static Datasource instance = new Datasource();

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);


            queryClientes = conn.prepareStatement(QUERY_CLIENTE);
            queryResumenes = conn.prepareStatement(QUERY_RESUMEN);
            queryFacturas = conn.prepareStatement(QUERY_FACTURA);
            queryResumenByClienteId = conn.prepareStatement(QUERY_RESUMEN_BY_CLIIENTE_ID);
            queryFacturasByResumenId = conn.prepareStatement(QUERY_FACTURAS_BY_RESUMEN_ID);

            insertIntoCliente = conn.prepareStatement(INSERT_CLIENTE, Statement.RETURN_GENERATED_KEYS);
            insertIntoResumen = conn.prepareStatement(INSERT_RESUMEN, Statement.RETURN_GENERATED_KEYS);
            insertIntoFactura = conn.prepareStatement(INSERT_FACTURA);

            updateClienteNombre = conn.prepareStatement(UPDATE_CLIENTE_NOMBRE);
            updateResumenTotales = conn.prepareStatement(UPDATE_RESUMEN_TOTALES);

            deleteCliente = conn.prepareStatement(DELETE_CLIENTE);
            deleteResumen = conn.prepareStatement(DELETE_RESUMEN);
            deleteFactura = conn.prepareStatement(DELETE_FACTURA);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {

            if(queryClientes != null) {
                queryClientes.close();
            }

            if(queryResumenes != null) {
                queryResumenes.close();
            }

            if(queryFacturas != null) {
                queryFacturas.close();
            }

            if(queryResumenByClienteId != null) {
                queryResumenByClienteId.close();
            }

            if(queryFacturasByResumenId != null) {
                queryFacturasByResumenId.close();
            }

            if(insertIntoCliente != null) {
                insertIntoCliente.close();
            }

            if(insertIntoResumen != null) {
                insertIntoResumen.close();
            }

            if(insertIntoFactura != null) {
                insertIntoFactura.close();
            }

            if(updateClienteNombre != null) {
                updateClienteNombre.close();
            }

            if(updateResumenTotales != null) {
                updateResumenTotales.close();
            }

            if(deleteCliente != null) {
                deleteCliente.close();
            }

            if(deleteResumen != null) {
                deleteResumen.close();
            }

            if(deleteFactura != null) {
                deleteFactura.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Cliente> queryClientes(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_CLIENTES);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_CLIENTE_NOMBRE);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Cliente> clientes = new ArrayList<>();
            while (results.next()) {
                try {
                    Thread.sleep(20);
                } catch(InterruptedException e) {
                    System.out.println("Interuppted: " + e.getMessage());
                }
                Cliente cliente = new Cliente();
                cliente.setId(results.getInt(INDEX_CLIENTE_ID));
                cliente.setNombre(results.getString(INDEX_CLIENTE_NOMBRE));
                cliente.setCuit(results.getString(INDEX_CLIENTE_CUIT));
                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Resumen> queryResumenForClienteId(int id) {
        try {
            queryResumenByClienteId.setInt(1, id);
            ResultSet results = queryResumenByClienteId.executeQuery();

            List<Resumen> resumenesMensuales = new ArrayList<>();
            while(results.next()) {
                Resumen resumen = new Resumen();
                resumen.setId(results.getInt(1));
                resumen.setClienteId(id);
                resumen.setYear(results.getInt(3));
                resumen.setMonth(results.getString(4));
                resumen.setPunto_venta(results.getInt(5));
                resumen.setGravado(results.getDouble(6));
                resumen.setIva(results.getDouble(7));
                resumen.setTotal(results.getDouble(8));

                resumenesMensuales.add(resumen);
            }

            return resumenesMensuales;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Factura> queryFacturasForResumenId(int id){
        try {
            queryFacturasByResumenId.setInt(1, id);
            ResultSet results = queryFacturasByResumenId.executeQuery();

            List<Factura> facturasMensuales = new ArrayList<>();
            while(results.next()) {

                Factura factura = new Factura();

                factura.setId(results.getInt(1));
                factura.setResumenId(id);
                factura.setFecha(results.getString(3));
                factura.setPuntoDeVenta(results.getInt(4));
                factura.setTipoFactura(results.getString(5));
                factura.setNumeroFactura(results.getInt(6));
                factura.setRazonSocial(results.getString(7));
                factura.setCuitDni(results.getString(8));
                factura.setImporteGrabados(results.getDouble(9));
                factura.setIva(results.getDouble(10));
                factura.setMontoTotalAbonado(results.getDouble(11));

                facturasMensuales.add(factura);
            }

            return facturasMensuales;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    //Insert

    public int insertCliente(String name, String cuit) throws SQLException {

        queryClientes.setString(1, name);
        ResultSet results = queryClientes.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert the clientes
            insertIntoCliente.setString(1, name);
            insertIntoCliente.setString(2, cuit);

            int affectedRows = insertIntoCliente.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }

            ResultSet generatedKeys = insertIntoCliente.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    public int insertResumen(int clienteId, int year, String month, int puntoDeVenta, double importeGravado, double iva, double totalAbonado) throws SQLException {

        queryResumenes.setInt(1, clienteId);
        queryResumenes.setInt(2, year);
        queryResumenes.setString(3, month);

        ResultSet results = queryResumenes.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {

            // Insert the resumenes
            insertIntoResumen.setInt(1, clienteId);
            insertIntoResumen.setInt(2, year);
            insertIntoResumen.setString(3, month);
            insertIntoResumen.setInt(4, puntoDeVenta);
            insertIntoResumen.setDouble(5, importeGravado);
            insertIntoResumen.setDouble(6, iva);
            insertIntoResumen.setDouble(7, totalAbonado);

            int affectedRows = insertIntoResumen.executeUpdate();

            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }

            ResultSet generatedKeys = insertIntoResumen.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    //Caso particular de solo tener q ingresar una unica factura, en general no usado porque se lee desde archivo mes entero
    public Boolean insertFactura(String cliente, String cuitCliente, Factura factura_) throws SQLException {

        try {
            conn.setAutoCommit(false);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            formatter = formatter.withLocale( Locale.getDefault() );
            LocalDate localDate = LocalDate.parse(factura_.getFecha(), formatter);

            int year = localDate.getYear();
            Month month = localDate.getMonth();

            int clienteId = insertCliente(cliente, cuitCliente);
            int resumenId = insertResumen(clienteId, year, month.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    factura_.getPuntoDeVenta(), factura_.getImporteGrabados(), factura_.getIva(),  factura_.getMontoTotalAbonado());
            insertIntoFactura.setInt(1, resumenId);
            insertIntoFactura.setString(2, factura_.getFecha());
            insertIntoFactura.setInt(3, factura_.getPuntoDeVenta());
            insertIntoFactura.setString(4, factura_.getTipoFactura());
            insertIntoFactura.setInt(5, factura_.getNumeroFactura());
            insertIntoFactura.setString(6, factura_.getRazonSocial());
            insertIntoFactura.setString(7, factura_.getCuitDni());
            insertIntoFactura.setDouble(8, factura_.getImporteGrabados());
            insertIntoFactura.setDouble(9, factura_.getIva());
            insertIntoFactura.setDouble(10, factura_.getMontoTotalAbonado());
            int affectedRows = insertIntoFactura.executeUpdate();
            if(affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("La insersion de la factura falló");
            }

        } catch(Exception e) {
            System.out.println("Insersion de la factura fallo: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("No se pudo realizar el rollback al estado anterior " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }
        }
        return null;
    }


    //Al modificar facturas ya que se recibe el resumen del archivo se agrega el pedido del resumen para agregarlo directamente      (Ver 1.2)
    public Boolean insertManyFactura(String cliente, String cuitCliente, Resumen resumen, List<Factura> facturas){

        try {
            conn.setAutoCommit(false);

            double importeGravadoTotales = 0;
            double ivaTotales = 0;
            double totalMontoAbonadoTotales = 0;
            int resumenIdForTotales = 0;

            for (Factura factura_ : facturas) {
                importeGravadoTotales += factura_.getImporteGrabados();
                ivaTotales += factura_.getIva();
                totalMontoAbonadoTotales += factura_.getMontoTotalAbonado();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                formatter = formatter.withLocale( Locale.getDefault() );
                LocalDate localDate = LocalDate.parse(factura_.getFecha(), formatter);

                int year = localDate.getYear();
                Month month = localDate.getMonth();

                int clienteId = insertCliente(cliente, cuitCliente);
                //Se los modifica ya que vienen los valores dados desde el archivo de duplicado de afip
                int resumenId = insertResumen(clienteId, year, month.getDisplayName(TextStyle.FULL, Locale.ENGLISH), factura_.getPuntoDeVenta(), resumen.getGravado(), resumen.getIva(), resumen.getTotal());
                insertIntoFactura.setInt(1, resumenId);
                insertIntoFactura.setString(2, factura_.getFecha());
                insertIntoFactura.setInt(3, factura_.getPuntoDeVenta());
                insertIntoFactura.setString(4, factura_.getTipoFactura());
                insertIntoFactura.setInt(5, factura_.getNumeroFactura());
                insertIntoFactura.setString(6, factura_.getRazonSocial());
                insertIntoFactura.setString(7, factura_.getCuitDni());
                insertIntoFactura.setDouble(8, factura_.getImporteGrabados());
                insertIntoFactura.setDouble(9, factura_.getIva());
                insertIntoFactura.setDouble(10, factura_.getMontoTotalAbonado());

                int affectedRows = insertIntoFactura.executeUpdate();
                if(affectedRows == 1) {
                    conn.commit();
                } else {
                    throw new SQLException("La insersion de la factura falló");
                }
            }
        } catch(Exception e) {
            System.out.println("Insersion de la factura fallo: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("No se pudo realizar el rollback al estado anterior " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }
        }
        return null;
    }

    //Upadtes

    public Boolean updateCliente(int id, String newName, String cuit) {
        try {
            updateClienteNombre.setString(1, newName);
            updateClienteNombre.setString(2, cuit);
            updateClienteNombre.setInt(3, id);
            int affectedRecords = updateClienteNombre.executeUpdate();

            return affectedRecords == 1;

        } catch(SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    public Boolean updateResumen(int id, double importeGrabado, double iva, double totalAbonado){
        try{
            updateResumenTotales.setDouble(1,importeGrabado);
            updateResumenTotales.setDouble(2,iva);
            updateResumenTotales.setDouble(3,totalAbonado);
            updateResumenTotales.setInt(4,id);

            int affectedRecords = updateResumenTotales.executeUpdate();

            return affectedRecords == 1;


        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }

    }

    //Delete

    public Boolean deleteClienteById(int clienteId){
        try {
            conn.setAutoCommit(false);

            deleteResumenByClienteId(clienteId);

            deleteCliente.setInt(1, clienteId);

            int affectedRecords = deleteCliente.executeUpdate();

            return affectedRecords == 1;

        }catch(Exception e) {
                System.out.println("Insersion de la factura fallo: " + e.getMessage());
                try {
                    System.out.println("Performing rollback");
                    conn.rollback();
                } catch(SQLException e2) {
                    System.out.println("No se pudo realizar el rollback al estado anterior " + e2.getMessage());
                }
            } finally {
                try {
                    System.out.println("Resetting default commit behavior");
                    conn.setAutoCommit(true);
                } catch(SQLException e) {
                    System.out.println("Couldn't reset auto-commit! " + e.getMessage());
                }
            }
            return null;
    }

    public Boolean deleteResumenByClienteId(int clienteId){
        try{
            conn.setAutoCommit(false);
            queryResumenByClienteId.setInt(1, clienteId);
            ResultSet results = queryResumenByClienteId.executeQuery();

            while(results.next()){
                int resumenId = results.getInt(1);
                deleteResumenByResumenId(resumenId);
            }
        }catch(Exception e) {
            System.out.println("Insersion de la factura fallo: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("No se pudo realizar el rollback al estado anterior " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }
        }
        return null;
    }

    public Boolean deleteResumenByResumenId(int resumenId){
        try{
            conn.setAutoCommit(false);

            deleteFacturaByResumenId(resumenId);

            deleteResumen.setInt(1,resumenId);

            int affectedRecords = deleteResumen.executeUpdate();

            if(affectedRecords == 1) {
                conn.commit();
            } else {
                throw new SQLException("La insersion de la factura falló");
            }
        }catch(Exception e) {
            System.out.println("Insersion de la factura fallo: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("No se pudo realizar el rollback al estado anterior " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behavior");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }
        }
        return null;
    }

    public Boolean deleteFacturaByResumenId(int resumenId){
        try{
            deleteFactura.setInt(1,resumenId);

            int affectedRecords = deleteFactura.executeUpdate();

            if(affectedRecords != 0){
                return true;
            }else{
                throw new SQLException("Delete de la factura falló");
            }
        }catch(SQLException e){
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }
}
