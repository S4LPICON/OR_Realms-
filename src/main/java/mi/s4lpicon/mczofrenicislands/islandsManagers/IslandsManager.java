package mi.s4lpicon.mczofrenicislands.islandsManagers;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.ArrayList;

public class IslandsManager implements Listener {

    static ArrayList<PlayerIsland> activeIslands = new ArrayList<>();

    public static void buildIsland(Player player){
        PlayerIsland island = new PlayerIsland(player, 1, "Esta Isla es la polla", "Gigante");
        activeIslands.add(island);
        island.generateWorld();
        player.sendMessage("You have created an island!");
    }

    @SuppressWarnings("deprecation")
    public static void sendPlayerToWorld(Player player, String worldName) {
        World world;

        // Get the world by name
        if (worldName.equals(player.getName())) {
            world = Bukkit.getWorld("PlayerIslands/" + worldName);
        } else {
            world = Bukkit.getWorld(worldName);
        }

        // If the world is not loaded, try to load it
        if (world == null) {
            world = loadWorld(player, worldName);
            player.sendMessage(ChatColor.RED + "The world was unloaded!"
                    + ChatColor.GREEN + "\nLoading World...");
            if (world == null) {
                player.sendMessage("The world could not be loaded " + worldName + ".");
                return;
            }
        }

        // Set the location in the world (0, 0, 0)
        Location location = new Location(world, 0, world.getHighestBlockYAt(0, 0)+1, 0);

        // Tp the player to the specified location
        player.teleport(location);
        player.sendMessage("You have teleported to the " + worldName + "'s island!");
    }

    public static World loadWorld(Player player, String worldName) {
        // Check if the world exists before loading it
        World world = Bukkit.getWorld("PlayerIslands/" + worldName);

        if (world == null) {
            // Check if the world directory exists
            if (new File(Bukkit.getWorldContainer(), "PlayerIslands/" + worldName).exists()) {

                world = Bukkit.createWorld(new WorldCreator("PlayerIslands/" + worldName));

                player.sendMessage("The world " + worldName + " has been loaded!");
            } else {
                player.sendMessage("The world " + worldName + " does not exist.");
            }
        } else {
            player.sendMessage("The world " + worldName + " is already loaded.");
        }
        return world;
    }
}
