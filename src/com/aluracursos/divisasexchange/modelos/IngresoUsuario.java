package com.aluracursos.divisasexchange.modelos;

public class IngresoUsuario {

    private float valorDivisa;
    private float nuevoValorDivisa;
    private String divisaActual;
    private String nuevaDivisa;

    public float getValorDivisa() {
        return valorDivisa;
    }

    public void setValorDivisa(float valorDivisa) {
        this.valorDivisa = valorDivisa;
    }

    public String getNuevaDivisa() {
        return nuevaDivisa;
    }

    public void setNuevaDivisa(String nuevaDivisa) {
        this.nuevaDivisa = nuevaDivisa;
    }

    public float getNuevoValorDivisa() {
        return nuevoValorDivisa;
    }

    public void setNuevoValorDivisa(float nuevoValorDivisa) {
        this.nuevoValorDivisa = nuevoValorDivisa;
    }

    public String getDivisaActual() {
        return divisaActual;
    }

    public void setDivisaActual(String divisaActual) {
        this.divisaActual = divisaActual;
    }
}
