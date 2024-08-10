package mi.s4lpicon.mczofrenicislands.islandsManagers;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class PlayerIsland {

    Player owner;
    int id;
    int spawn_x, spawn_y, spawn_z;
    String type;
    String size;
    World theWorld;
    //permissions

    public PlayerIsland(Player player, int id, String type, String size){
        this.owner = player;
        this.id = id;
        this.type = type;
        this.size = size;
        generateWorld();
    }

    public void generateWorld() {

        // Specify the world name
        String worldName = "PlayerIslands/" + this.owner.getName();

        // Set up the world creator
        WorldCreator creator = new WorldCreator(worldName);
        creator.environment(World.Environment.NORMAL); // Environment type
        creator.type(WorldType.NORMAL); // world type
        //End of the world generator


        if (this.theWorld == null) {
            // Create the world
            World world = creator.createWorld();

            // Set the edge of the world
            assert world != null;
            WorldBorder border = world.getWorldBorder();
            border.setCenter(0, 0);
            border.setSize(500);
            border.setWarningDistance(10);
            border.setDamageAmount(2.0);
            this.theWorld = world;
        }else {
            owner.sendMessage("Error the world is already created");
        }
    }



}
