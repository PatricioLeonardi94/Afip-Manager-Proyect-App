package com.company.java.model.Excel;

import org.apache.poi.ss.usermodel.Row;

public interface excelManagment {

//    Pasando una row del excel la manera en la que cada clase implementara el metodo de completar esa row con sus datos
    void toExcel(Row row);

}
