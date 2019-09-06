package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.cscorelib2.config.Config;
import io.github.thebusybiscuit.cscorelib2.updater.GitHubBuildsUpdater;
import io.github.thebusybiscuit.cscorelib2.updater.Updater;
import org.bstats.bukkit.Metrics;

public class SlimefunFix extends JavaPlugin implements CommandExecutor, Listener {
    private static SlimefunFix instance;

    @Override
    public void onEnable() {
        if (Bukkit.getServer().getPluginManager().getPlugin("CS-CoreLib") == null || Bukkit.getServer().getPluginManager().getPlugin("Slimefun") == null) {
            Bukkit.getLogger().warning("The plugins, CS-CoreLib and Slimefun are needed for this plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;
        Config config = new Config(this);

        new Metrics(this);
        //Updater updater = new GitHubBuildsUpdater(this, getFile(), "ajan-12/SlimefunFix/master");
        //if (getConfig().getBoolean("options.auto-update")) updater.start();

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