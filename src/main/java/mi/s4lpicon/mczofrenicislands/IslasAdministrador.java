package mi.s4lpicon.mczofrenicislands;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class IslasAdministrador implements Listener {

    static ArrayList<JugadorIsla> islasActivas = new ArrayList<>();

    public static void crearIsla(Player jugador){
        JugadorIsla isla = new JugadorIsla(jugador, 1, "Esta Isla es la polla", "Gigante");
        islasActivas.add(isla);
        isla.crearMundo();
    }

    public static void teleportPlayerToWorld(Player player, String nombreMundo) {
        World mundo;

        // Obtener el mundo por nombre
        if (nombreMundo.equals(player.getName())) {
            mundo = Bukkit.getWorld("IslasJugadores/" + nombreMundo);
        } else {
            mundo = Bukkit.getWorld(nombreMundo);
        }

        // Si el mundo no está cargado, intenta cargarlo
        if (mundo == null) {
            mundo = cargarMundo(player, nombreMundo);
            player.sendMessage(ChatColor.RED + "El mundo estaba descargado!"
                    + ChatColor.GREEN + "\nCargando Mundo...");
            if (mundo == null) {
                player.sendMessage("No se pudo cargar el mundo " + nombreMundo + ".");
                return;
            }
        }

        // Definir la ubicación en el mundo (0, 0, 0)
        Location location = new Location(mundo, 0, mundo.getHighestBlockYAt(0, 0), 0);

        // Teletransportar al jugador a la ubicación especificada
        player.teleport(location);
        player.sendMessage("¡Te has teletransportado al mundo " + nombreMundo + "!");
    }

    public static World cargarMundo(Player player, String nombreMundo) {
        // Verificar si el mundo existe antes de cargarlo
        World mundo = Bukkit.getWorld("IslasJugadores/" + nombreMundo);
        if (mundo == null) {
            // Comprueba si el directorio del mundo existe
            if (new File(Bukkit.getWorldContainer(), "IslasJugadores/" + nombreMundo).exists()) {
                mundo = Bukkit.createWorld(new WorldCreator("IslasJugadores/" + nombreMundo));
                player.sendMessage("¡El mundo " + nombreMundo + " ha sido cargado!");
            } else {
                player.sendMessage("El mundo " + nombreMundo + " no existe.");
            }
        } else {
            player.sendMessage("El mundo " + nombreMundo + " ya está cargado.");
        }
        return mundo;
    }
}
