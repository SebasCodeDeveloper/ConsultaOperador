package com.automatizacion.View;

public class ConsolaView {

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.err.println("⚠ " + error);
    }

}
