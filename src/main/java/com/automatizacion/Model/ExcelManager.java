package com.automatizacion.Model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPCION GENRAL DE LA CLASE:
 * La clase {@code ExcelManager} sencargada de gestionar la lectura y escritura en el archivo Excel utilizado
 * en el proceso de automatización.

   ESTA CLASE PERMITE
 *  Leer una lista de números telefónicos desde una hoja específica.
 *  Registrar el operador correspondiente a cada número.

   Configuración utilizada:
 * Números se leen desde la columna C (índice 2).
 * Operadores se escriben en la columna B (índice 1).

 * @author Jhoan Sebastoian Peña Ordoñez
 * @version 1.0
 * @since 06/11/2025
 * */

public class ExcelManager {


    public static List<String> leerContactos(String rutaExcel) {
        List<String> contactos = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(rutaExcel);
             Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheet("Hoja1");

            if (sheet == null) {
                System.err.println("⚠ No se encontró la hoja 'Hoja1'. Verifica el nombre.");
                return contactos;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // empieza en fila 1 (evita título)
                Row fila = sheet.getRow(i);
                if (fila == null) continue;
                Cell celda = fila.getCell(2); // Columna C (índice 2)
                if (celda == null)
                    continue;

                // ✅ Convertir número correctamente evitando notación científica
                String numero = "";
                if (celda.getCellType() == CellType.NUMERIC) {
                    numero = String.valueOf((long) celda.getNumericCellValue());
                } else {
                    numero = celda.getStringCellValue().trim();
                }

                if (!numero.isEmpty()) {
                    contactos.add(numero);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactos;
    }

    /**
     * Escribe en la hoja el operador detectado para un número.
     *
     * @param rutaExcel Ruta del archivo Excel.
     * @param fila Número de fila en la cual escribir.
     * @param operador Operador identificado.
     */

    public static void escribirOperador(String rutaExcel, int fila, String operador) {
        try (FileInputStream file = new FileInputStream(rutaExcel);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet("Hoja1");
            Row row = sheet.getRow(fila);
            Cell cell = row.createCell(10); // Columna B
            cell.setCellValue(operador);

            try (FileOutputStream out = new FileOutputStream(rutaExcel)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
