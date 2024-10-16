package com.example.ejercicio1;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;

//Clase encargada de contener el metodo para serializar las instancias en XML
public class SerializadorXML {

    /**
     * @param reserva para definir que reserva se va a serializar
     * @param archivoDestino para definir en que archivo se va a guardar la instancia serializada
     */
    public static void serializarReservaXML(Reserva reserva, String archivoDestino) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(archivoDestino))) {
            encoder.writeObject(reserva);
            System.out.println("Reserva guardada en formato XML");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
