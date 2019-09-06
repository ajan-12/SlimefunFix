package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
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
        if (utils.getGUI() != null) {
            if (utils.isGUI(event.getView())) {
                InventoryHolder player = event.getClickedInventory().getHolder();
                if (player == null) {

                    if (event.getSlotType() == InventoryType.SlotType.CONTAINER) {
                        boolean isValid = true;
                        for (Integer[] invalidSlots : utils.getInvalidSlots()) {
                            for (Integer invalidSlot : invalidSlots) {
                                if (invalidSlot == event.getSlot()) {
                                    isValid = false;
                                    break;
                                }
                            }
                            if (!isValid) break;
                        }

                        if (isValid) {
                            SlimefunFixTask task = new SlimefunFixTask(event.getWhoClicked(), event.getView());
                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task);
                        } else {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryPutItemDrag(InventoryDragEvent event) {
        Utils utils = new Utils();
        if (utils.getGUI() != null) {
            if (utils.isGUI(event.getView())) {
                event.setCancelled(true);
            }
        }
    }

}
