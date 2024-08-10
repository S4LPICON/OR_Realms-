package mi.s4lpicon.mczofrenicislands.commandsManager;

import mi.s4lpicon.mczofrenicislands.islandsManagers.IslandsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandsManager implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("islands")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length == 0) {
                    player.sendMessage("Correct use: /islands <args>");
                    return false;
                }

                if (args[0].equalsIgnoreCase("create")) {

                    IslandsManager.buildIsland(player);

                    return true;

                } else if (args[0].equalsIgnoreCase("tp")) {

                    if (args.length >= 2) {
                        String targetName = args[1].equals(player.getName()) ? player.getName() : args[1];
                        IslandsManager.sendPlayerToWorld(player,targetName);
                    } else {
                        //He sends him to his own island
                        IslandsManager.sendPlayerToWorld(player, player.getName());
                    }
                    return true;

                } else {
                    player.sendMessage("Correct use: /islands create or /islands tp");
                    return false;
                }
            } else {
                sender.sendMessage("This command can only be executed by one player.");
                return false;
            }
        }
        return false;
    }
}
