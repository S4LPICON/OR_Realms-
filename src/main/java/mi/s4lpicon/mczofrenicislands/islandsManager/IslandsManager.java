package mi.s4lpicon.mczofrenicislands.islandsManager;

import mi.s4lpicon.mczofrenicislands.fileManagement.JsonUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/*
NO BORRAR ESTA INFORMATION HASTA NO SER IMPLEMENTADA
modo para descargar un mundo

World world = Bukkit.getWorld("nombre_del_mundo");
if (world != null) {
    Bukkit.unloadWorld(world, false); // false para no guardar los cambios en el mundo antes de descargarlo
}


modo para saber cuantos jugadores hay en un mundo
World world = Bukkit.getWorld("nombre_del_mundo");
if (world != null) {
    int playerCount = world.getPlayers().size();
    // Aquí puedes usar playerCount como desees
}

falta

/island member -> modifica a un miembro especifico como cambiarle los permisos o banearlo
/island members -> muestra todos los miembros de la isla en un inventario y al que se le de click abre el /island member
/island settings -> un inventario con 3 casillas la primera para manejar los jugadores, la segunda para reiniciar la isla
y la tercera para controlar quien entra a la isla opciones: cualquiera, miembros, friends y miembros (imposible añadir aca), nadie y siempre abierto (solo rango)

inventarios
cuando invita a un jugador seleccionar permisos
cuando se hace /island member inventario para las opciones del comando
cuando se hace /island members para mostrar todos los jugadores miembros
cuando se hace /island settings para las opciones de este commando

 */

public class IslandsManager implements Listener {

    public static ArrayList<PlayerIsland> activeIslands = new ArrayList<>();

    public static void getDevInfo(Player player, int pos) {
        if (activeIslands.get(pos) != null) {
            player.sendMessage("Active Islands: " + activeIslands.get(pos).toString());
        }else {
            player.sendMessage("Esa pocision de isla no existe");
        }
    }
    public static void getDevInfo(Player player) {

        player.sendMessage("Active Islands: " + activeIslands);
    }

    public static void removePlayerOfIsland(String player, String owner){
        Player playerToRemove = Bukkit.getPlayer(player);
        Player playerOwner = Bukkit.getPlayer(owner);
        assert playerOwner != null;
        PlayerIsland island = vefAndLoadIsland(playerOwner);
        if (island == null){
            //aqui cargaria la isla y si no hay nadie la descargo
            playerOwner.sendMessage("Error la isla no existe!");
            return;
        }
        if(playerToRemove == null){
            playerOwner.sendMessage("Error con el jugador a eliminar");
            return;
        }
        int pos = findIsland(playerOwner);
        if (pos == -1){
            playerOwner.sendMessage("Error al remover jugador de la isla");
            return;
        }
        playerOwner.sendMessage(activeIslands.get(pos).removePlayerMember(player));
        playerOwner.sendMessage("Se ha eliminado el jugador "+ playerToRemove.getName()+" de la isla!");
    }

    public static void setPlayerPermissionIsland(Player ownerIsland, String player, int newPermissionLevel){
        Player player1 = Bukkit.getPlayer(player);
        int pos = findIsland(ownerIsland);
        if (pos == -1){
            ownerIsland.sendMessage("Error island unloaded!");
            return;
        }
        PlayerIsland island = activeIslands.get(pos);
        ownerIsland.sendMessage(island.setPlayerPermissions(player, newPermissionLevel));
    }
    public static void leaveIsland(Player player, String islandOwner){
        Player playerOwner = Bukkit.getPlayer(islandOwner);

        PlayerIsland island = vefAndLoadIsland(playerOwner);
        if (island == null){
            //aqui cargaria la isla y si no hay nadie la descargo
            player.sendMessage("Error la isla no existe!");
            return;
        }
        int pos = findIsland(playerOwner);
        if (pos == -1){
            player.sendMessage("Error al remover jugador de la isla");
            return;
        }
        activeIslands.get(pos).removePlayerMember(player.getName());
        playerOwner.sendMessage("El jugador "+player.getName()+" ha salido de tu isla!");
        player.sendMessage("Has salido correctamente de la isla de "+ islandOwner+"!");
    }

    public static void addPlayerToIsland(String player, String owner, int levelPermission){

        Player playerToInvite = Bukkit.getPlayer(player);
        Player playerOwner = Bukkit.getPlayer(owner);
        int pos = findIsland(playerOwner);
        if (pos == -1 && playerOwner != null){
            playerOwner.sendMessage("Error al invitar el jugador a la isla");
            return;
        }
        if (activeIslands.get(pos).findBannedPlayer(player) != -1){
            assert playerOwner != null;
            playerOwner.sendMessage("El jugador se encuentra baneado de tu isla!");
            return;
        }
        assert playerOwner != null;
        PlayerIsland island = vefAndLoadIsland(playerOwner);
        if (island != null) {
            activeIslands.get(pos).addPlayerMember(player, levelPermission);
        }else {
            playerOwner.sendMessage("you don't have an active island!");
        }
    }

    @SuppressWarnings("deprecation")
    public static void buildIsland(Player player){
        File carpeta = new File("PlayerIslands/"+player.getName());
        if(carpeta.exists() && carpeta.isDirectory()){
            player.sendMessage(ChatColor.RED+"Ya tienes un mundo creado no se puede crear otro!");
            return;
        }
        String size;
        if (player.hasPermission("mczofrenicisland.elite") || player.isOp()){
            size = "BIG";
        } else if (player.hasPermission("mczofrenicisland.pro")) {
            size = "MEDIUM";
        }else {
            size = "SMALL";
        }
        PlayerIsland island = new PlayerIsland(player.getName(), 1, "NORMAL", size);
        activeIslands.add(island);
        // Guardar el objeto en un archivo JSON
        sendPlayerToWorld(player, player.getName());
        player.sendMessage("You have created an island!");
    }

    public static PlayerIsland vefAndLoadIsland(Player owner){
        if (owner == null){
            return null;
        }
        if (Bukkit.getWorld("PlayerIslands/" + owner.getName()) == null){
            return null;
        }
        int pos = findIsland(owner);
        if (pos == -1){
            loadIsland(owner, owner.getName());
            return activeIslands.get(findIsland(owner));
        }else {
            return activeIslands.get(pos);
        }
    }

    public static void banPlayerOfIsland(Player owner, String bannedPlayer){
        Player player2Ban = Bukkit.getPlayer(bannedPlayer);
        assert player2Ban != null;
        if(player2Ban.isOp() || player2Ban.hasPermission("mczofrenicisland.admin")){
            owner.sendMessage("No puedes banear a un administrador!");
            return ;
        }
        PlayerIsland island = vefAndLoadIsland(owner);
        if (island != null) {
            island.banPlayer(bannedPlayer);
        }else {
            owner.sendMessage("you don't have an active island!");
        }
    }

    public static void unBanPlayerOfIsland(Player owner, String unBannedPlayer){
        PlayerIsland island = vefAndLoadIsland(owner);
        if (island != null) {
            island.unBanPlayer(unBannedPlayer);
        }else {
            owner.sendMessage("you don't have an active island!");
        }
    }

    @SuppressWarnings("deprecation")
    public static void sendPlayerToWorld(Player player, String worldName) {
        Player playerIslandOwner = Bukkit.getPlayer(worldName);
        int islandPos = findIsland(playerIslandOwner);

        if (!Objects.requireNonNull(playerIslandOwner).isOnline() && (islandPos == -1 || !player.getName().equals(worldName))) {
            player.sendMessage("¡Error! The island is disabled.");
            return;
        }

        if (islandPos != -1) {
            if (activeIslands.get(islandPos).findBannedPlayer(player.getName()) != -1
                    && (!player.isOp() && !player.hasPermission("mczofrenicisland.admin"))) {
                player.sendMessage("¡Error, you are banned from this island!");
                return;
            }
        }

        World world = Bukkit.getWorld("PlayerIslands/" + worldName);

        // If the world is not loaded, try to load it
        if (world == null) {
            world = loadIsland(player, worldName);
            player.sendMessage(ChatColor.RED + "The world was unloaded!"
                    + ChatColor.GREEN + "\nLoading World...");
            if (world == null) {
                player.sendMessage("The world could not be loaded " + worldName + ".");
                return;
            }
        }
        islandPos = findIsland(playerIslandOwner);
        //PlayerIsland island = JsonUtils.leerJugadorIslaDesdeJson("plugins/MCzofrenic-Islands/"+player.getName()+"_island.json");
        if (islandPos == -1){
            loadIsland(player, worldName);
        }
        islandPos = findIsland(playerIslandOwner);
        if(islandPos != -1) {
            double x = activeIslands.get(islandPos).getSpawnX();
            double y = activeIslands.get(islandPos).getSpawnY()+1;
            double z = activeIslands.get(islandPos).getSpawnZ();
            // Set the location in the world (0, 0, 0)
            Location location = new Location(world, x, y, z);

            // Tp the player to the specified location
            player.teleport(location);
            player.sendMessage("You have teleported to the " + worldName + "'s island!");
        }else {
            player.sendMessage("Ocurrio un error al mandarte a esa isla!");
        }


    }
    public static void sendPlayerToSpawn(Player player){
        String spawnName = "LaCapital";
        double x = 10,y= -59,z=23;
        // Set the location in the world (0, 0, 0)
        World world = Bukkit.getWorld(spawnName);
        Location location = new Location(world, x, y, z);
        location.setYaw(-180);
        location.setPitch(0);

        // Tp the player to the specified location
        player.teleport(location);
    }

    public static World loadIsland(Player player, String worldName) {
        PlayerIsland island = JsonUtils.leerJugadorIslaDesdeJson("plugins/MCzofrenic-Islands/"+player.getName()+"_island.json");
        if(island != null){
            activeIslands.add(island);
        }

        player.sendMessage("Active islands before load world: "+activeIslands);
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

    public static int findIsland(Player player){
        for (PlayerIsland island : activeIslands){
            if (island != null && (island.getOwnerName().equals(player.getName()))) {
                return activeIslands.indexOf(island);
            }
        }
        return -1;
    }

    public static void setIslandSpawn(Player player){
        if (!(player.getWorld().getName().equals("PlayerIslands/"+player.getName()))){
            player.sendMessage("Debes estar en tu propia isla para utilizar este comando!");
            return;
        }

        int posIsland = findIsland(player);
        if (posIsland == -1){
            PlayerIsland island = JsonUtils.leerJugadorIslaDesdeJson("plugins/MCzofrenic-Islands/"+player.getName()+"_island.json");
            activeIslands.add(island);
            player.sendMessage("No fue posible encontrar tu isla");
            return;
        }
        PlayerIsland isla = activeIslands.get(posIsland);


        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        isla.setSpawnX(x);
        isla.setSpawnY(y);
        isla.setSpawnZ(z);
        player.sendMessage("Has designado el spawn con éxito!");
    }
}
