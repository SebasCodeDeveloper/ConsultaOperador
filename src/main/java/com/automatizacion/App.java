package com.automatizacion;

import com.automatizacion.Controller.BusquedaContactos;
import com.automatizacion.Model.ConfigManager;
import com.automatizacion.Model.ExcelManager;
import com.automatizacion.View.ConsolaView;

/**
 * Clase principal de ejecución del proyecto.
 * La clase {@code App} actúa como punto de entrada de la aplicación.

 * Se encarga de inicializar los componentes principales y de coordinar el flujo
 * para iniciar el proceso de automatización de consulta de operadores móviles.

 * Responsabilidades:
 * Obtener la ruta del archivo Excel desde {@code config.properties}.
 * Inicializar las clases del modelo, la vista y el controlador.
 * Ejecutar el proceso a través del controlador {@code BusquedaContactos}.

 * Ejemplo de ejecución:
 * java -jar ConsultaOperador.jar

 * @author Jhoan Sebastián Peña Ordoñez
 * @version 1.0
 * @since 06/11/2025
 */

public class App {

    /**
     * Método principal (entry point) de la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        // Obtiene la ruta del archivo Excel desde el archivo config.properties
        String rutaExcel = ConfigManager.get("ruta.excel");

        ExcelManager excel = new ExcelManager();
        ConsolaView vista = new ConsolaView();
        BusquedaContactos controlador = new BusquedaContactos(excel, vista, rutaExcel);

        // Ejecuta el proceso principal
        controlador.ejecutar();
    }
}
