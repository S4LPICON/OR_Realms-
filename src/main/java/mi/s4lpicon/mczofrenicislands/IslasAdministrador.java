package mi.s4lpicon.mczofrenicislands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class IslasAdministrador implements Listener {

    static ArrayList<JugadorIsla> islas = new ArrayList<>();

    public static void crearIsla(Player jugador){
        JugadorIsla isla = new JugadorIsla(jugador, 1, "Esta Isla es la polla", "Gigante");
        islas.add(isla);
        isla.crearMundo();

    }

    public static void teleportPlayerToWorld(Player player) {
        // Obtener el mundo por nombre
        World world = Bukkit.getWorld("IslasJugadores/" +player.getName());

        if (world == null) {
            player.sendMessage("El mundo " + player.getName() + " no está cargado.");
            return;
        }

        // Definir la ubicación en el mundo (0, 0, 0)
        Location location = new Location(world, 0, world.getHighestBlockYAt(0, 0), 0);

        // Teletransportar al jugador a la ubicación especificada
        player.teleport(location);
    }
}
