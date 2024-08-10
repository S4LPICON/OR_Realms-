package mi.s4lpicon.mczofrenicislands.islandsManager;

import com.google.gson.annotations.Expose;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class PlayerIsland {

    private Player owner;
    @Expose
    private String ownerName;
    @Expose
    private int id;
    @Expose
    private double spawn_x, spawn_y, spawn_z;
    @Expose
    private String type;
    @Expose
    private String size;
    private World theWorld;

    public PlayerIsland(Player player, int id, String type, String size){
        this.owner = player;
        this.id = id;
        this.type = type;
        this.size = size;
        this.ownerName = player.getName();
        generateWorld();
    }

    public void generateWorld() {
        // Specify the world name
        String worldName = "PlayerIslands/" + this.owner.getName();

        // Set up the world creator
        WorldCreator creator = new WorldCreator(worldName);
        creator.environment(World.Environment.NORMAL); // Environment type
        creator.type(WorldType.FLAT); // World type

        // Check if the world already exists
        World existingWorld = Bukkit.getWorld(worldName);
        if (existingWorld != null) {
            this.theWorld = existingWorld;
            owner.sendMessage("World already exists and loaded.");
            return;
        }

        // Create the world
        World world = creator.createWorld();
        if (world != null) {
            this.theWorld = world;

            // Set the edge of the world
            WorldBorder border = world.getWorldBorder();
            border.setCenter(0, 0);
            border.setSize(500);
            border.setWarningDistance(10);
            border.setDamageAmount(2.0);

            owner.sendMessage("World created successfully.");
        } else {
            owner.sendMessage("Error creating the world.");
        }
    }

    // Getters and setters
    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        if (owner != null) {
            this.ownerName = owner.getName();
        }
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSpawnX() {
        return spawn_x;
    }

    public void setSpawnX(double spawn_x) {
        this.spawn_x = spawn_x;
    }

    public double getSpawnY() {
        return spawn_y;
    }

    public void setSpawnY(double spawn_y) {
        this.spawn_y = spawn_y;
    }

    public double getSpawnZ() {
        return spawn_z;
    }

    public void setSpawnZ(double spawn_z) {
        this.spawn_z = spawn_z;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public World getTheWorld() {
        return theWorld;
    }

    public void setTheWorld(World theWorld) {
        this.theWorld = theWorld;
    }
}
