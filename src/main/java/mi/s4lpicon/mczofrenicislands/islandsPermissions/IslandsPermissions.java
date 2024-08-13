package mi.s4lpicon.mczofrenicislands.islandsPermissions;

import mi.s4lpicon.mczofrenicislands.islandsManager.IslandsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IslandsPermissions {

    public static String getStringAfterSlash(String input) {
        // Dividir el string en partes utilizando el carácter '/'
        String[] parts = input.split("/");

        // Verificar si hay al menos dos partes
        if (parts.length > 1) {
            // Retornar la parte después del '/'
            return parts[1];
        } else {
            // Si no hay '/' en el string, retornar el string original o manejar el caso como desees
            return input;
        }
    }

    public static boolean blockActionPlayer(Player player){
        if(player.isOp() || player.hasPermission("mczofrenicisland.admin")){
            return false;
        }
        String playerOwnerName = IslandsPermissions.getStringAfterSlash(player.getWorld().getName());
        Player islandOwner = Bukkit.getPlayer(playerOwnerName);
        assert islandOwner != null;
        IslandsManager.vefAndLoadIsland(islandOwner);
        int pos = IslandsManager.findIsland(islandOwner);
        if (pos == -1){
            player.sendMessage("Mega error detectado reportalo!"); //es imposible que el mundo en el que se encuentra, esté descargado
            return false;
        }
        if (IslandsManager.activeIslands.get(pos).hasPermissions(player.getName()) <= 1
                && !(player.getName().equals(playerOwnerName))) {
            return true;
        }
        return false;
    }
}
