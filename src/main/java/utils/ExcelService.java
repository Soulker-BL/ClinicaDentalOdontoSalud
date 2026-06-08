/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import config.Conexion;
import java.io.FileOutputStream;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService {

    public void exportarHistorialPacientes(String rutaArchivo) {

        String sql = "SELECT * FROM pacientes";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Pacientes");

            // 🔵 ESTILOS

            // Estilo encabezado
            CellStyle headerStyle = wb.createCellStyle();
            Font headerFont = wb.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // Bordes
            CellStyle borderStyle = wb.createCellStyle();
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            // 🔷 ENCABEZADOS
            String[] columnas = {
                "DNI", "Nombre", "Apellido", "Fecha Nac.",
                "Teléfono", "Email", "Dirección", "Estado"
            };

            Row header = sheet.createRow(0);

            for (int i = 0; i < columnas.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // 🔷 DATOS
            int fila = 1;

            while (rs.next()) {

                Row row = sheet.createRow(fila++);

                row.createCell(0).setCellValue(rs.getString("dni"));
                row.createCell(1).setCellValue(rs.getString("nombre"));
                row.createCell(2).setCellValue(rs.getString("apellido"));
                row.createCell(3).setCellValue(rs.getString("fecha_nacimiento"));
                row.createCell(4).setCellValue(rs.getString("telefono"));
                row.createCell(5).setCellValue(rs.getString("email"));
                row.createCell(6).setCellValue(rs.getString("direccion"));
                row.createCell(7).setCellValue(rs.getString("estado_tratamiento"));

                // aplicar bordes a cada celda
                for (int i = 0; i < 8; i++) {
                    row.getCell(i).setCellStyle(borderStyle);
                }
            }

            // 🔷 AUTO AJUSTE COLUMNAS
            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 🔷 GUARDAR
            FileOutputStream fileOut = new FileOutputStream(rutaArchivo);
            wb.write(fileOut);
            fileOut.close();
            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}