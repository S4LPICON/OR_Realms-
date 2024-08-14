package mi.s4lpicon.mczofrenicislands.islandsPermissions.listeners;

import mi.s4lpicon.mczofrenicislands.islandsPermissions.IslandsPermissions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEntityListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // Verifica si el atacante es un jugador
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Entity target = event.getEntity();

            // Verifica si el atacante tiene permisos para realizar acciones en la isla
            if (IslandsPermissions.blockActionPlayer(attacker, 2)) {

                // Si el objetivo es otro jugador
                if (target instanceof Player) {
                    Player targetPlayer = (Player) target;

                    // Verifica si ambos jugadores son residentes de la misma isla
                    if (arePlayersInSameIsland(attacker, targetPlayer)) {
                        event.setCancelled(true); // Cancela el evento, bloqueando el ataque
                    }
                } else {
                    // Si el objetivo es una entidad no jugadora
                    // Bloquea ataques a mascotas y animales de granja si no son residentes de la misma isla
                    if (isPetOrFarmAnimal(target)) {
                        event.setCancelled(true); // Cancela el evento, bloqueando el ataque
                    }
                }
            }
        }
    }

    private boolean arePlayersInSameIsland(Player attacker, Player targetPlayer) {
        return IslandsPermissions.blockActionPlayer(attacker, 2)
                && IslandsPermissions.blockActionPlayer(targetPlayer, 2);
    }

    // Método para verificar si la entidad es un mob hostil
    private boolean isHostileMob(Entity entity) {
        switch (entity.getType()) {
            case ZOMBIE:
            case SKELETON:
            case CREEPER:
            case SPIDER:
            case ENDERMAN:
            case WITCH:
            case BLAZE:
            case SLIME:
            case ENDER_DRAGON:
            case WITHER:
            case GHAST:
            case PILLAGER:
            case VINDICATOR:
            case EVOKER:
                // Agrega más mobs hostiles según sea necesario
                return true;
            default:
                return false;
        }
    }

    // Método para verificar si la entidad es una mascota o un animal de granja
    private boolean isPetOrFarmAnimal(Entity entity) {
        switch (entity.getType()) {
            case WOLF:
            case CAT:
            case HORSE:
            case DONKEY:
            case MULE:
            case LLAMA:
            case PARROT:
            case SHEEP:
            case COW:
            case PIG:
            case CHICKEN:
            case RABBIT:
            case TURTLE:
            case BEE:
            case FOX:
            case PANDA:
            case POLAR_BEAR:
            case STRIDER:
            case GOAT:
            case FROG:
            case CAMEL:
                // Agrega más mascotas y animales de granja según sea necesario
                return true;
            default:
                return false;
        }
    }
}
