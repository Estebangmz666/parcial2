package com.example.ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String LOG_FILE_PATH = "log.txt";
    private static final String CODIGO_FILE_PATH = "codigo.txt";

    public static void main(String[] args) {

        List<Habitacion> habitaciones = new ArrayList<>();
    
        Habitacion hab1 = new Habitacion(101, "Sencilla");
        Habitacion hab2 = new Habitacion(102, "Doble");
    
        habitaciones.add(hab1);
        habitaciones.add(hab2);
    
        int codigo = leerCodigo();
    
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String fechaReserva = scanner.nextLine();
    
        System.out.println("Ingrese el total de la reserva: ");
        double total = scanner.nextDouble();
        scanner.nextLine();
    
        System.out.println("Ingrese el tipo de habitación (Sencilla/Doble): ");
        String tipoHabitacion = scanner.nextLine();
    
        System.out.println("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine();
    
        System.out.println("Ingrese el número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
    
        Reserva reserva = new Reserva(fechaReserva, total, tipoHabitacion, cliente, numeroHabitacion, codigo + 1);
    
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numeroHabitacion) {
                habitacion.agregarCodigoReserva(codigo + 1);
                break;
            }
        }
    
        String archivoXML = "reserva.xml";
        String archivoBinario = "reserva.bin";
    
        SerializadorXML.serializarReservaXML(reserva, archivoXML);
        SerializadorBinario.serializarReservaBinario(reserva, archivoBinario);
    
        logToFile("INFO", "Usuario hizo una reserva con codigo: " + (codigo + 1));
    
        guardarCodigo(codigo + 1);
    
        ArchivoHabitaciones.guardarHabitacionesEnArchivo(habitaciones);
    
        scanner.close();
    }
    

    public static void logToFile(String level, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(String.format("%s [%s]: %s%n", timestamp, level, message));
        } catch (IOException e) {
            System.out.println("Error al escribir en el log: " + e.getMessage());
        }
    }

    private static int leerCodigo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CODIGO_FILE_PATH))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de código. Iniciando en 0.");
            return 0;
        }
    }

    private static void guardarCodigo(int codigo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CODIGO_FILE_PATH))) {
            writer.write(String.valueOf(codigo));
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de código: " + e.getMessage());
        }
    }
}