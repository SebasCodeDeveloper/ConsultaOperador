package com.automatizacion.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * La clase {@code ConfigManager} se encarga de cargar y gestionar las configuraciones
 * definidas en el archivo {@code config.properties}, ubicado dentro del directorio
 * {@code resources}.

 * Esta clase facilita el acceso a valores externos como:
   Rutas de archivos (por ejemplo, Excel)
   URLs usadas en la automatización
   Parámetros generales configurables

 * El archivo de configuración debe llamarse {@code config.properties}
 * y estar ubicado en:
 * src/main/resources/config.properties

 * Uso:
 * String url = ConfigManager.get("web.url");
 * String excel = ConfigManager.get("excel.path");

 * @author Jhoan
 * @version 1.0
 * @since 06/11/2025
 */
public class ConfigManager {

    private static Properties properties = new Properties();

    // Carga el archivo de propiedades al iniciar la clase
    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                System.err.println("⚠ No se encontró el archivo config.properties dentro de resources.");
            } else {
                properties.load(input);
            }

        } catch (IOException e) {
            System.err.println("⚠ Error cargando config.properties: " + e.getMessage());
        }
    }

    /**
     * Obtiene el valor de una clave definida en el archivo de configuración.
     *
     * @param key clave a buscar en el archivo .properties
     * @return valor asociado a la clave, o {@code null} si no existe
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
