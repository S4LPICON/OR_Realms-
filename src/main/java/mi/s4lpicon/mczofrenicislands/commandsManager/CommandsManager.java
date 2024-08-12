package mi.s4lpicon.mczofrenicislands.commandsManager;

import mi.s4lpicon.mczofrenicislands.islandsManager.IslandsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandsManager implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("island")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length == 0) {
                    player.sendMessage("Correct use: /island <args>");
                    return false;
                }

                //THE SUBCOMMANDS
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

                } else if (args[0].equalsIgnoreCase("setspawn")) {
                    IslandsManager.setIslandSpawn(player);
                    return true;

                } else if (args[0].equalsIgnoreCase("ban")) {
                    if (args.length >= 2) {
                        IslandsManager.banPlayerOfIsland(player, args[1]);

                    } else {
                        player.sendMessage("Write the name of a player to ban him!");
                    }
                    return true;

                }else if (args[0].equalsIgnoreCase("unban")) {
                    if (args.length >= 2) {
                        IslandsManager.unBanPlayerOfIsland(player, args[1]);
                    } else {
                        player.sendMessage("Write the name of a player to unban him!");
                    }
                    return true;

                }else {
                    player.sendMessage("Correct use: /island create or /island tp");
                    return false;
                }
            } else {
                sender.sendMessage("This command can only be executed by one player.");
                return false;
            }
        }
        if (command.getName().equalsIgnoreCase("spawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                IslandsManager.sendPlayerToSpawn(player);
                player.sendMessage("You have teleported to spawn!");
            }
        }

        if (command.getName().equalsIgnoreCase("devinfo")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                IslandsManager.getDevInfo(player);
            }
        }
        return false;
    }
}
