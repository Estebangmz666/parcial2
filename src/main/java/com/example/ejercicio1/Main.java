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

//Metodo principal de la aplicación
public class Main {

    /*
     * LOG_FILE_PATH define la ruta del log
     * CODIGO_FILE_PATH define la ruta del archivo en donde se van a guardar los codigos usados, para ir de 1 en 1
     */
    private static final String LOG_FILE_PATH = "log.txt";
    private static final String CODIGO_FILE_PATH = "codigo.txt";

    public static void main(String[] args) {

        //Se inicializa una lista vacia de habitaciones
        List<Habitacion> habitaciones = new ArrayList<>();
    
        //Se crean dos habitaciones default
        Habitacion hab1 = new Habitacion(101, "Sencilla");
        Habitacion hab2 = new Habitacion(102, "Doble");
    
        //Se añaden a la lista creada anteriormente
        habitaciones.add(hab1);
        habitaciones.add(hab2);
    
        //Se leen el ultimo codigo registrado en "codigo.txt" para que la nueva instancia de reserva tenga como codigo codigo+1
        int codigo = leerCodigo();
        
        //Se inicializa el scanner
        Scanner scanner = new Scanner(System.in);
    
        //Se le pide al usuario la fecha de la reserva en formato YYYY-MM-DD
        System.out.println("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        //Se declara la fecha de la reserva con la entrada de usuario
        String fechaReserva = scanner.nextLine();
    
        //Se le pide al usuario el total del dinero de la reserva
        System.out.println("Ingrese el total de la reserva: ");
        //Se declara el total de la reserva con la entrada del usuario
        double total = scanner.nextDouble();
        //Salto de linea del scanner
        scanner.nextLine();
    
        System.out.println("Ingrese el tipo de habitación (Sencilla/Doble): ");
        String tipoHabitacion = scanner.nextLine();
    
        System.out.println("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine();
    
        System.out.println("Ingrese el número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
    
        //Se instancia un objeto de tipo Reserva con los datos proporcionados del usuario
        Reserva reserva = new Reserva(fechaReserva, total, tipoHabitacion, cliente, numeroHabitacion, codigo + 1);
    
        //Se usa un ciclo for each para verificar que numero de habitación desea al usuario. Posteriormente se le asigna como codigo codigo+1
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numeroHabitacion) {
                habitacion.agregarCodigoReserva(codigo + 1);
                break;
            }
        }
        
        //Se declara la ubicación de los archivos a serializar
        String archivoXML = "reserva.xml";
        String archivoBinario = "reserva.bin";
        
        //Se serializan en XML y Binario
        SerializadorXML.serializarReservaXML(reserva, archivoXML);
        SerializadorBinario.serializarReservaBinario(reserva, archivoBinario);
    
        //Se escribe en el log la reserva hecha
        logToFile("INFO", "Usuario hizo una reserva con codigo: " + (codigo + 1));
    
        //Se guarda el ultimo codigo asignado a la ultima habitación instanciada
        guardarCodigo(codigo + 1);
        
        //Se agrega a la lista habitaciones la ultima habitación instanciada
        ArchivoHabitaciones.guardarHabitacionesEnArchivo(habitaciones);
        
        //Se cierra el scanner
        scanner.close();
    }
    
    /*
     * Metodo para escribir en el log
     * @param LOG_FILE_PATH define la ubicación del archivo log.txt
     * Se instancia fileWriter como (ruta, true) para añadir y no sobreescribir el archivo
     * Se escribe en el archivo con formato YYYY-MM-DD HH-MM-SS [Level] Message
     */
    public static void logToFile(String level, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(String.format("%s [%s]: %s%n", timestamp, level, message));
        } catch (IOException e) {
            //Manejo de excepciones
            System.out.println("Error al escribir en el log: " + e.getMessage());
        }
    }

    //Metodo para leer el ultimo codigo
    private static int leerCodigo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CODIGO_FILE_PATH))) {
            String line = reader.readLine();
            //Si el archivo codigo.txt no existe, lo crea e inicializa el codigo en 0
            return line != null ? Integer.parseInt(line) : 0;
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de código. Iniciando en 0.");
            return 0;
        }
    }

    //Metodo para escribir el ultimo codigo en codigo.txt
    private static void guardarCodigo(int codigo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CODIGO_FILE_PATH))) {
            writer.write(String.valueOf(codigo));
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de código: " + e.getMessage());
        }
    }
}