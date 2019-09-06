package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.config.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import java.util.Arrays;
import java.util.logging.Logger;

final class Utils {

    void reload() {
        info("Reloading the plugin...");
        LOGGER = null;

        Config config = new Config(SlimefunFix.getInstance());
        setupGUI(config);

        info("Successfully reloaded the plugin.");
    }

    void reset() {
        info("Resetting the stored values...");
        gui = null;
        guiName = null;
        info("Reset the stored values.");
    }

    private Logger LOGGER = null;

    void info(String... messages) {
        if (LOGGER == null) LOGGER = Logger.getLogger("SlimefunFix");
        Arrays.stream(messages).forEachOrdered(LOGGER::info);
    }

    void warn(String... messages) {
        if (LOGGER == null) LOGGER = Logger.getLogger("SlimefunFix");
        Arrays.stream(messages).forEachOrdered(LOGGER::warning);
    }



    private Integer[] INPUT_BORDER = {0, 1, 2, 3, 9, 12, 18, 19, 20, 21};
    private Integer[] INPUT_SLOTS = {10, 11};

    private Integer[] OUTPUT_BORDER = {5, 6, 7, 8, 14, 17, 23, 24, 25, 26};
    private Integer[] OUTPUT_SLOTS = {15, 16};

    private Integer[] OTHER_SLOTS = {4};
    private Integer[] HELP_SLOT = {13};
    private Integer[] HEAD_SLOT = {22};

    private Integer[][] INVALID_SLOTS = {INPUT_BORDER, OUTPUT_BORDER, OTHER_SLOTS, HELP_SLOT, HEAD_SLOT};
    private static Inventory gui;
    private static String guiName;

    boolean setupGUI(Config config) {
        try {
            guiName = config.getString("options.gui-name");
            if (guiName == null) guiName = getDefaultGUIName();

            Inventory inv = Bukkit.createInventory(null, 27, guiName);

            ItemStack iB = new CustomItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, ChatColor.AQUA + "Put your broken items here.");
            for (Integer integer : INPUT_BORDER) {
                inv.setItem(integer, iB);
            }

            ItemStack iR = new CustomItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Take your fixed items from here.");
            for (Integer integer : OUTPUT_BORDER) {
                inv.setItem(integer, iR);
            }

            ItemStack iLG = new CustomItem(Material.GREEN_STAINED_GLASS_PANE, " ");
            for (Integer integer : OTHER_SLOTS) {
                inv.setItem(integer, iLG);
            }

            ItemStack iG = new CustomItem(Material.GREEN_STAINED_GLASS_PANE, ChatColor.YELLOW + "How to use this?",
                    ChatColor.AQUA + "Put a broken slimefun machine inside.", ChatColor.DARK_AQUA + "Example broken slimefun machine:");
            for (Integer integer : HELP_SLOT) {
                inv.setItem(integer, iG);
            }

            ItemStack iH = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&eCS-CoreLib's Head");
            for (Integer integer : HEAD_SLOT) {
                inv.setItem(integer, iH);
            }

            gui = inv;

        } catch (Exception e) {
            warn("An error occurred while trying to create the GUI.", e.toString());
        }

        return gui != null;
    }

    String getGUIName() { return guiName; }
    Inventory getGUI() { return gui; }
    boolean isGUI(InventoryView iv) { return iv.getTitle().equals(getGUIName()); }

    private String getDefaultGUIName() {
        return ChatColor.YELLOW + "[" + ChatColor.GREEN + "SFFIX" + ChatColor.YELLOW + "] " + ChatColor.RED + " Item fixing GUI.";
    }

    Integer[] getInputSlots() { return INPUT_SLOTS; }
    Integer[] getOutputSlots() { return OUTPUT_SLOTS; }
    Integer[][] getInvalidSlots() { return INVALID_SLOTS; }
}
