package mi.s4lpicon.mczofrenicislands.fileManagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mi.s4lpicon.mczofrenicislands.islandsManager.PlayerIsland;


import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarJugadorIslaEnJson(PlayerIsland jugadorIsla) {
        if (jugadorIsla == null) {
            // Maneja el caso donde jugadorIsla es null, tal vez con un mensaje de error o alguna otra lógica.
            System.out.println("Error: jugadorIsla es null. No se puede guardar en JSON.");
            return;
        }
        String rutaArchivo = "plugins/MCzofrenic-Islands/" + jugadorIsla.getOwnerName() + "_island.json";
        // Crear una instancia de Gson con configuración para incluir solo campos anotados con @Expose
        // y formatear el JSON de manera legible.
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() // Solo incluir campos anotados con @Expose
                .setPrettyPrinting() // Para imprimir JSON de manera legible
                .create();

        // Convertir el objeto a JSON y guardarlo en el archivo.
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(jugadorIsla, writer);
            System.out.println("Datos guardados en " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }

    public static PlayerIsland leerJugadorIslaDesdeJson(String rutaArchivo) {
        try (FileReader reader = new FileReader(rutaArchivo)) {
            return gson.fromJson(reader, PlayerIsland.class);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return null;
        }
    }
}