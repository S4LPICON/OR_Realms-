package mi.s4lpicon.mczofrenicislands;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MCzofrenic_Islands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("islands")).setExecutor(new Comandos());
        // Registra el TabCompleter
        Objects.requireNonNull(getCommand("islands")).setTabCompleter(new IslaTabCompleter());
        // Registrar eventos
        PluginManager pm = getServer().getPluginManager();
        //pm.registerEvents(new Manager(), this);
        getLogger().info("El plugin se ha habilitado correctamente");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("El plugin se ha deshabilitado correctamente");
    }
}
