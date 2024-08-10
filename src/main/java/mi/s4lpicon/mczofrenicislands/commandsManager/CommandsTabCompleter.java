package mi.s4lpicon.mczofrenicislands.commandsManager;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandsTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        // Verify that the command is /islands
        if (command.getName().equalsIgnoreCase("island")) {
            if (args.length == 1) {
                // Complete first level subcommands
                completions.add("create");
                completions.add("tp");
                completions.add("ban");
            }if (args.length == 2 && args[0].equalsIgnoreCase("tp")) {
                List<String> playerNames = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    playerNames.add(player.getName());
                }
                return playerNames; // Retorna la lista de nombres de jugadores
            }
        }
        return completions;
    }
}

