package mi.s4lpicon.mczofrenicislands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class IslaTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        // Verificar que el comando es /islands
        if (command.getName().equalsIgnoreCase("islands")) {
            if (args.length == 1) {
                // Completar subcomandos de primer nivel
                completions.add("crear");
                completions.add("tp");
                completions.add("ban");
            } else if (args.length == 2) {
                // Completar subcomandos de segundo nivel si es necesario
                if (args[0].equalsIgnoreCase("tp")) {
                    completions.add("<nombre_mundo>");
                }
            }
        }

        return completions;
    }
}

