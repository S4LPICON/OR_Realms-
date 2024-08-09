package mi.s4lpicon.mczofrenicislands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comandos implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("islands")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length == 0) {
                    player.sendMessage("Uso correcto: /islands crear o /islands tp");
                    return false;
                }

                if (args[0].equalsIgnoreCase("crear")) {
                    IslasAdministrador.crearIsla(player);
                    player.sendMessage("¡Has creado una isla!");
                    return true;
                } else if (args[0].equalsIgnoreCase("tp")) {
                    if (args.length >= 2) {
                        String targetName = args[1].equals(player.getName()) ? player.getName() : args[1];
                        IslasAdministrador.teleportPlayerToWorld(player, targetName);
                        player.sendMessage("¡Te has teletransportado a la isla de " + targetName + "!");
                    } else {
                        //Lo deberia teletrasnporta a su propia isla
                        player.sendMessage("Uso correcto: /islands tp <nombreJugador>");
                    }
                    return true;
                } else {
                    player.sendMessage("Uso correcto: /islands crear o /islands tp");
                    return false;
                }
            } else {
                sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
                return false;
            }
        }
        return false;
    }
}
