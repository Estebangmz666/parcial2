package com.example.ejercicio1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//Clase encargada de contener el metodo para serializar las instancias en Binario
public class SerializadorBinario {

    /**
     * @param reserva para definir que reserva se va a serializar
     * @param archivoDestino para definir en que archivo se va a guardar la instancia serializada
     */
    public static void serializarReservaBinario(Reserva reserva, String archivoDestino) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDestino))) {
            oos.writeObject(reserva);
            System.out.println("Reserva guardada en formato binario");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
