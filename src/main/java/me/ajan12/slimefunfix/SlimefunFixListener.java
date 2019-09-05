package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;

class SlimefunFixListener implements Listener {

    private Plugin plugin;

    SlimefunFixListener(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryPutItemClick(InventoryClickEvent event) {
        Utils utils = new Utils();
        if (utils.isGUI(event.getView())) {
            SlimefunFixTask task = new SlimefunFixTask(event.getWhoClicked(), event.getView());
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task);
        }
    }

    @EventHandler
    public void onInventoryPutItemDrag(InventoryDragEvent event) {
        Utils utils = new Utils();
        if (utils.isGUI(event.getView())) {
            SlimefunFixTask task = new SlimefunFixTask(event.getWhoClicked(), event.getView());
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task);
        }
    }
}
