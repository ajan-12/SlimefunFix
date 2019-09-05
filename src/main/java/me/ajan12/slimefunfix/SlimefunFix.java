package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.bstats.bukkit.Metrics;

public class SlimefunFix extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        if (Bukkit.getServer().getPluginManager().getPlugin("CS-CoreLib") == null || Bukkit.getServer().getPluginManager().getPlugin("Slimefun") == null) {
            Bukkit.getLogger().warning("The plugins, CS-CoreLib and Slimefun are needed for this plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        saveDefaultConfig();

        new Metrics(this);
        //Updater updater = new GitHubBuildsUpdater(this, getFile(), "ajan-12/SlimefunFix/master");
        //if (getConfig().getBoolean("options.auto-update")) updater.start();

        new SlimefunFixCommands();
        new SlimefunFixListener(this);
    }

}