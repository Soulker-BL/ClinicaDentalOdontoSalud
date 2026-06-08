/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class ExcelGenerator {

    public static void generarExcel(String usuario){
        
        try {

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Usuarios");

            Row row = sheet.createRow(0);

            row.createCell(0).setCellValue("Usuario");
            row.createCell(1).setCellValue("Estado");
            row.createCell(2).setCellValue("Fecha y Hora");

            Row row1 = sheet.createRow(1);

            row1.createCell(0).setCellValue(usuario);
            row1.createCell(1).setCellValue("Acceso Correcto");
            row1.createCell(2).setCellValue(
            java.time.LocalDateTime.now().toString()
);

            FileOutputStream file =
                    new FileOutputStream(
        "Reporte_Login_" + usuario + ".xlsx"
);

            workbook.write(file);

            workbook.close();
            file.close();

            System.out.println("Excel generado");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
