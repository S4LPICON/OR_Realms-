package mi.s4lpicon.mczofrenicislands;

import mi.s4lpicon.mczofrenicislands.commandsManager.CommandsManager;
import mi.s4lpicon.mczofrenicislands.commandsManager.CommandsTabCompleter;
import mi.s4lpicon.mczofrenicislands.fileManagement.JsonUtils;
import mi.s4lpicon.mczofrenicislands.islandsManager.IslandsManager;
import mi.s4lpicon.mczofrenicislands.islandsManager.PlayerIsland;
import mi.s4lpicon.mczofrenicislands.islandsPermissions.listeners.DamageEntityListener;
import mi.s4lpicon.mczofrenicislands.islandsPermissions.listeners.IslandsEventListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MCzofrenic_Islands extends JavaPlugin {

    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        // Registro de listeners
        getServer().getPluginManager().registerEvents(new IslandsEventListener(), this);
        getServer().getPluginManager().registerEvents(new DamageEntityListener(), this);
        // plugin startup logic
        Objects.requireNonNull(this.getCommand("island")).setExecutor(new CommandsManager());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new CommandsManager());
        Objects.requireNonNull(this.getCommand("devinfo")).setExecutor(new CommandsManager());
        // Register the TabCompleter
        Objects.requireNonNull(getCommand("island")).setTabCompleter(new CommandsTabCompleter());
        // Log events
        PluginManager pm = getServer().getPluginManager();
        //pm.registerEvents(new Manager(), this);
        getLogger().info(ChatColor.YELLOW + "The plugin has been successfully enabled");

    }

    @Override
    public void onDisable() {
        // Logica cuando el plugin es deshabilitado
        saveAllInfo();
        getLogger().info("The plugin has been successfully disabled");
    }


    public void saveAllInfo(){
        //save all island info in json
        for (PlayerIsland island : IslandsManager.activeIslands){
            JsonUtils.guardarJugadorIslaEnJson(island);
        }

    }
}
