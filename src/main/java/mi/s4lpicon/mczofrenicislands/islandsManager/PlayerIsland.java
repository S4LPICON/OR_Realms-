package mi.s4lpicon.mczofrenicislands.islandsManager;

import com.google.gson.annotations.Expose;
import mi.s4lpicon.mczofrenicislands.worldGeneration.GenericWorldGeneration;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class PlayerIsland {

    private Player owner;
    @Expose
    private String ownerName;
    @Expose
    private final ArrayList<String> bannedPlayers = new ArrayList<>();
    @Expose
    private final ArrayList<String> residentPlayers = new ArrayList<>();
    @Expose
    private final ArrayList<String> trustedPlayers = new ArrayList<>();
    @Expose
    private int id;
    @Expose
    private double spawn_x, spawn_y, spawn_z;
    @Expose
    private String type;
    @Expose
    private String size;
    private World theWorld;

    public PlayerIsland(String player, int id, String type, String size){

        this.owner = Bukkit.getPlayer(player);
        this.id = id;
        this.type = type;
        this.size = size;
        this.ownerName = player;
        this.spawn_x = rNum();
        this.spawn_z = rNum();
        generateWorld();
        this.spawn_y = theWorld.getHighestBlockYAt(0, 0);
    }
    private int rNum(){
        Random random = new Random();
        int min = 0;
        int max = 255;
        return random.nextInt((max - min) + 1) + min;
    }

    public String setPlayerPermissions(String player, int levelPermission){
        vefOwner();
        int actualPermi = hasPermissions(player);
        if ( actualPermi < 1){
            return "El jugador aun no tiene permisos en tu isla!";
        } else if (actualPermi == 1) {
            //es residente
            residentPlayers.removeIf(name -> name.equals(player));
        } else if (actualPermi == 2) {
            //es trusted
            trustedPlayers.removeIf(name -> name.equals(player));
        }
        switch (levelPermission) {
            case 1:
                residentPlayers.add(player);
                break;
            case 2:
                trustedPlayers.add(player);
                break;
        }
        return "Se ha cambiado correctamente los permisos!";
    }
    public int hasPermissions(String player){
        for(String name : residentPlayers){
            if (name.equals(player)){
                return 1;
            }
        }

        for(String name : trustedPlayers){
            if (name.equals(player)){
                return 2;
            }
        }
        return 0;

    }

    public void addPlayerMember(String player, int permissionLevel){
        vefOwner();
        if (hasPermissions(player) > 0){
            this.owner.sendMessage("El jugador ya tiene permisos");
            return;
        }
        if (permissionLevel == 1){
            residentPlayers.add(player);
        } else if (permissionLevel == 2) {
            trustedPlayers.add(player);
        }else {
            this.owner.sendMessage("Ese nivel de permisos no existe!");
            return;
        }
        this.owner.sendMessage("Se ha añadido correctamente a "+ player+"!");
    }

    public String removePlayerMember(String player){
        vefOwner();
        int levelPermission = hasPermissions(player);
        if (levelPermission < 1){
            return "Este jugador no tiene permisos en tu isla!";
        } else if (levelPermission == 1) {
            residentPlayers.removeIf(name -> name.equals(player));
        } else if (levelPermission == 2) {
            trustedPlayers.removeIf(name -> name.equals(player));
        }
        return "Error al eliminar permisos";

    }

    public void vefOwner(){
        if (this.owner == null){
            this.owner = Bukkit.getPlayer(this.ownerName);
        }
    }

    public void generateWorld() {
        // Specify the world name
        String worldName = "PlayerIslands/" + this.owner.getName();


        // Check if the world already exists
        World existingWorld = Bukkit.getWorld(worldName);
        if (existingWorld != null) {
            this.theWorld = existingWorld;
            owner.sendMessage("World already exists and loaded.");
            return;
        }

        World world = GenericWorldGeneration.genGenericWorld(this.owner, worldName, this.type, this.size, this.spawn_x, this.spawn_z);
        if (world != null){
            this.theWorld = world;
        }
    }

    public void banPlayer(String player){
        vefOwner();
        if (player.equals(this.ownerName)){
            this.owner.sendMessage("No puedes banearte de tu propia isla!");
            return;
        }
        if (findBannedPlayer(player) != -1){
            this.owner.sendMessage("Este jugador ya esta baneado!");
            return;
        }
        Player playerBanned = Bukkit.getPlayer(player);
        // Obtén el mundo del jugador
        World playerWorld = Objects.requireNonNull(playerBanned).getWorld();

        // Compara el nombre del mundo con el nombre que estás buscando
        if(playerWorld.getName().equals("PlayerIslands/" +this.ownerName)){
            IslandsManager.sendPlayerToSpawn(Objects.requireNonNull(playerBanned));
            playerBanned.sendMessage("Te han baneado de esta isla!, enviandote al spawn!");
        }
        this.bannedPlayers.add(player);
        removePlayerMember(player);
        this.owner.sendMessage("Has baneado a "+player);
    }

    public void unBanPlayer(String player){
        vefOwner();
        if (player.equals(this.ownerName)){
            this.owner.sendMessage("No puedes banearte de tu propia isla!");
            return;
        }
        if (findBannedPlayer(player) == -1){
            this.owner.sendMessage("Aun no has baneado a este jugador!");
            return;
        }

        this.bannedPlayers.remove(findBannedPlayer(player));
        this.owner.sendMessage("Has desbaneado a "+player);
    }

    public int findBannedPlayer(String player){
        for(String name : bannedPlayers){
            if (name.equals(player)){
                return bannedPlayers.indexOf(name);
            }
        }
        return -1;
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

    @Override
    public String toString() {
        return "PlayerIsland{" +
                "ownerName='" + ownerName + '\'' +
                ", \nbannedPlayers=" + bannedPlayers +
                ", \nresidentPlayers=" + residentPlayers +
                ", \ntrustedPlayers=" + trustedPlayers +
                ", \nid=" + id +
                ", \nspawn_x=" + spawn_x +
                ", \nspawn_y=" + spawn_y +
                ", \nspawn_z=" + spawn_z +
                ", \ntype='" + type + '\'' +
                ", \nsize='" + size + '\'' +
                '}';
    }
}
