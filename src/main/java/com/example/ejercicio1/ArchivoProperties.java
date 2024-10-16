package com.example.ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Clase encargada de gestionar propiedades de "config.properties"
public class ArchivoProperties {

    private static Properties properties = new Properties();

    //Metodo que carga las propiedades de "config.properties"
    public static void cargarPropiedades() {
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
            System.out.println("Propiedades cargadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de propiedades: " + e.getMessage());
        }
    }

    //Metodo para obtener la ruta del archivo XML en el que se van a serializar las reservas
    public static String getRutaArchivoXML() {
        return properties.getProperty("rutaArchivoXML");
    }

    //Metodo para obtener la ruta del archivo bin en el que se van a serializar las reservas
    public static String getRutaArchivoBinario() {
        return properties.getProperty("rutaArchivoBinario");
    }

    //Metodo para obtener la ruta del archivo log.txt
    public static String getRutaArchivoLog() {
        return properties.getProperty("rutaArchivoLog");
    }

    //Metodo para obtener el tipo de habitación que se usara en la reserva
    public static String getTipoHabitacion1() {
        return properties.getProperty("tipoHabitacion1");
    }

    //Metodo para obtener el tipo de habitación que se usara en la reserva
    public static String getTipoHabitacion2() {
        return properties.getProperty("tipoHabitacion2");
    }
}