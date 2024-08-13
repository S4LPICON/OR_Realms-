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
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Entity target = event.getEntity();

            // Verifica si el jugador tiene permisos
            if (IslandsPermissions.playerCanDoThis(player)) {

                // Permitir atacar solo mobs hostiles
                if (isHostileMob(target)) {
                    return; // No cancela el evento, permite el ataque
                }

                // Bloquear ataques a mascotas y animales de granja
                if (isPetOrFarmAnimal(target)) {
                    event.setCancelled(true); // Cancela el evento, bloqueando el ataque
                }
            }
        }
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
