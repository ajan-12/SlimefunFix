package me.ajan12.slimefunfix;

import org.bukkit.ChatColor;
import org.bukkit.inventory.InventoryView;

import java.util.Arrays;
import java.util.logging.Logger;

final class Utils {

    private Logger LOGGER = null;

    boolean isGUI(InventoryView iv) {
        return iv.getTitle().equals(getGUIName());
    }

    String getGUIName() {
        return ChatColor.RED + "Put your broken sf items inside.";
    }

    void info(String... messages) {
        if (LOGGER == null) LOGGER = Logger.getLogger("SlimefunFix");
        Arrays.stream(messages).forEachOrdered(LOGGER::info);
    }

    void warn(String... messages) {
        if (LOGGER == null) LOGGER = Logger.getLogger("SlimefunFix");
        Arrays.stream(messages).forEachOrdered(LOGGER::warning);
    }
}
