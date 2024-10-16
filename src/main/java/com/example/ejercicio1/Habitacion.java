package com.example.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private int numero;
    private String tipoHabitacion;
    private List<Integer> codigosReservas;

    public Habitacion(int numero, String tipoHabitacion) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.codigosReservas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public List<Integer> getCodigosReservas() {
        return codigosReservas;
    }

    public void agregarCodigoReserva(int codigoReserva) {
        this.codigosReservas.add(codigoReserva);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numero).append("@").append(tipoHabitacion);
        for (Integer codigo : codigosReservas) {
            sb.append("@").append(codigo);
        }
        return sb.toString();
    }
}