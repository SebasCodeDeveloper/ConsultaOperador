package com.automatizacion.View;

/**
 * La clase {@code ConsolaView} se encarga de gestionar la salida de mensajes en la consola.

 * Es utilizada como capa de visualización (Vista) dentro del patrón MVC, proporcionando
 * métodos para mostrar mensajes informativos y mensajes de error durante la ejecución
 * del proceso de automatización.

 * Responsabilidades:
 * Mostrar mensajes estándar al usuario.
 * Mostrar advertencias o errores con formato diferenciado.

 * Ejemplo de uso:
 * ConsolaView view = new ConsolaView();
 * view.mostrarMensaje("Proceso iniciado...");
 * view.mostrarError("No se pudo cargar el archivo.");

 * @author Jhoan Sebastián Peña Ordoñez
 * @version 1.0
 * @since 06/11/2025
 */
public class ConsolaView {

    /**
     * Muestra un mensaje informativo en la consola.
     *
     * @param mensaje texto que se desea mostrar al usuario
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra un mensaje de error en la consola, precedido de
     * un ícono de advertencia para facilitar su identificación.
     *
     * @param error descripción del error ocurrido
     */
    public void mostrarError(String error) {
        System.err.println("⚠ " + error);
    }

}
