package mi.s4lpicon.mczofrenicislands.islandsPermissions.listeners;// Importa los paquetes necesarios

import mi.s4lpicon.mczofrenicislands.islandsPermissions.IslandsPermissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

public class IslandsEventListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (IslandsPermissions.playerCanDoThis(event.getPlayer())) {
            event.setCancelled(true); // Cancela el evento, evitando que el jugador rompa bloques
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (IslandsPermissions.playerCanDoThis(event.getPlayer())) {
            event.setCancelled(true); // Cancela el evento, evitando que el jugador coloque bloques
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (IslandsPermissions.playerCanDoThis(event.getPlayer())) {
            event.setCancelled(true); // Cancela el evento, evitando que el jugador interactúe con bloques
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (IslandsPermissions.playerCanDoThis(event.getPlayer())) {
            event.setCancelled(true); // Cancela el evento, evitando que el jugador interactúe con entidades
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (IslandsPermissions.playerCanDoThis((Player) event.getPlayer())) {
            event.setCancelled(true); // Cancela el evento, evitando que el jugador abra inventarios
        }
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        if (IslandsPermissions.playerCanDoThis(event.getPlayer())) {
            event.setCancelled(true); // Cancela el evento, evitando que el jugador vacíe un balde
        }
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        if (IslandsPermissions.playerCanDoThis(event.getPlayer())) {
            event.setCancelled(true); // Cancela el evento, evitando que el jugador llene un balde
        }
    }




}
