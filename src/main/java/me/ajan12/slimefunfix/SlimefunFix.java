package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.cscorelib2.config.Config;
import org.bstats.bukkit.Metrics;

public class SlimefunFix extends JavaPlugin implements CommandExecutor, Listener {
    private static SlimefunFix instance;

    @Override
    public void onEnable() {
        instance = this;
        final Config config = new Config(this);

        if (Bukkit.getServer().getPluginManager().getPlugin("CS-CoreLib") == null || Bukkit.getServer().getPluginManager().getPlugin("Slimefun") == null) {
            final String message = config.getString("missing-dependency");
            Bukkit.getLogger().warning(message == null ? "The plugins, CS-CoreLib and Slimefun are needed for this plugin!" : message);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new Metrics(this);

        Messages.setupMessages(config);
        new Utils().setupGUI(config);
        new SlimefunFixCommands();
        new SlimefunFixListener(this);
    }

    @Override
    public void onDisable() {
        instance = null;
        new Utils().reset();
    }

    static SlimefunFix getInstance() { return instance; }

}