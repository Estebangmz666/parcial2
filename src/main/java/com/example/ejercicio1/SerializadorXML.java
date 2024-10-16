package com.example.ejercicio1;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerializadorXML {

    public static void serializarReservaXML(Reserva reserva, String archivoDestino) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(archivoDestino))) {
            encoder.writeObject(reserva);
            System.out.println("Reserva guardada en formato XML");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
