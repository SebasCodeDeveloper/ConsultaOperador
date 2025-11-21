package com.automatizacion.Controller;

import com.automatizacion.Locators.DoctorSimLocators;
import com.automatizacion.Model.ConfigManager;
import com.automatizacion.Model.ExcelManager;
import com.automatizacion.View.ConsolaView;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * DESCRIPCION GENRAL DE LA CLASE:
* La clase {@code BusquedaContactos} se encaraga de realizar la auotomatizaçión
 * del proceso de cansulta de operador telefonco para una lista de numeros almacenados
 * en el archivo excel.

  PARA CADA NUMERO ENCONTRADO.
 * ingresa al sitio web configurado.
 * Envia el numero al campo corespondiente.
 * Leer el operador detectado en la pagina.
 * Registarr dicho operador nuevamente en el archivo excel.

 * Esta clase utiliza Selenium WebDriver para la automatización del navegador.

 * @author Jhoan Sebastoian Peña Ordoñez
 * @version 1.0
 * @since 06/11/2025
 * */


public class BusquedaContactos {
    private ExcelManager excelManager;
    private ConsolaView vista;
    private String rutaExcel
            ;

/**
 * Constructor principal que inicializan las dependencias nesearias
 * @param  excelManager Maneja la lectura y escritura de mi archivo excel.
 * @param vista  Vista clase responsable en mostar mensajes en consola.
 * @param rutaExcel Ruta de archivo excel que contiene los numeros.
 * */

    public BusquedaContactos(ExcelManager excelManager, ConsolaView vista, String rutaExcel) {
        this.excelManager = excelManager;
        this.vista = vista;
        this.rutaExcel = rutaExcel;
    }


    /**
     * METODO QUE REALIZA EL PASO A PASO DEL PROCESO DE AUTOMATIZACION.
     * Optiene los numeros desde el archivo excel.
     * Inicializa el sitio web configurado.
     * Introduce cada numero y obtiene el operador.
     * Guarda el resultado nueva mente en el excel.
     *
     *
     * */

    public void ejecutar() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            List<String> contactos = excelManager.leerContactos(rutaExcel);

            for (int i = 0; i < contactos.size(); i++) {
                String numero = contactos.get(i);
                vista.mostrarMensaje("🔍 Buscando número: " + numero);


                String url = ConfigManager.get("web.url");
                driver.get(url);
                WebElement input = driver.findElement(DoctorSimLocators.INPUT_NUMERO);
                input.clear();
                input.sendKeys(numero);
                driver.findElement(DoctorSimLocators.BOTON_SIGUIENTE).click();
                Thread.sleep(4000);

                // Leer operador
                String operadorResult;
                try {

                    WebElement operadorElemento =  driver.findElement(DoctorSimLocators.OPERADOR);
                    operadorResult = operadorElemento.getText().trim().toUpperCase();
                    System.out.println("Operador: " + operadorResult);
                } catch (NoSuchElementException e) {
                    operadorResult = "NO DETECTADO";
                    System.out.println("⚠ No se encontró operador.");
                }

                excelManager.escribirOperador(rutaExcel, i + 1, operadorResult);
                vista.mostrarMensaje("✔ " + numero + " → " + operadorResult + "\n");
            }

            vista.mostrarMensaje("✅ Proceso completado correctamente.");
            driver.quit();

        } catch (Exception e) {
            vista.mostrarError("Error durante la ejecución: " + e.getMessage());
        }
    }
}
