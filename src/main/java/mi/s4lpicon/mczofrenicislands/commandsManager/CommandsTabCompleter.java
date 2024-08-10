package mi.s4lpicon.mczofrenicislands.commandsManager;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandsTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        // Verify that the command is /islands
        if (command.getName().equalsIgnoreCase("islands")) {
            if (args.length == 1) {
                // Complete first level subcommands
                completions.add("create");
                completions.add("tp");
                completions.add("ban");
            } //else if (args.length == 2) {
                // Complete second level subcommands if necessary
               //if (args[0].equalsIgnoreCase("tp")) {
                    //completions.add("<Player>");
                //}
            //}
        }
        return completions;
    }
}

