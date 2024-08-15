package mi.s4lpicon.mczofrenicislands.worldGeneration;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;


public class GenericWorldGeneration {

    public static World genGenericWorld(Player player, String worldName, String type, String size, double x, double z){



        WorldCreator creator = new WorldCreator(worldName);

        World.Environment environment = World.Environment.NORMAL; // Default environment

// Set the environment type based on the input
        switch (type) {
            case "INFERNAL":
                environment = World.Environment.NETHER;
                break;
            case "FINAL":
                environment = World.Environment.THE_END;
                break;
            default:
                break;
        }

        creator.environment(environment);
        creator.type(WorldType.FLAT); // World type

        // Create the world
        World world = creator.createWorld();
        if (world != null) {

            // Set the edge of the world
            WorldBorder border = world.getWorldBorder();
            border.setCenter(x, z);
            switch (size){
                case "SMALL":
                    border.setSize(200);
                    break;
                case "MEDIUM":
                    border.setSize(225);
                    break;
                case "BIG":
                    border.setSize(250);
                    break;
                default:
                    break;
            }

            border.setWarningDistance(-2);
            border.setDamageAmount(2.0);

            player.sendMessage("World created successfully xd.");
            return world;
        } else {
            player.sendMessage("Error creating the world.");
        }
        return null;
    }
}
