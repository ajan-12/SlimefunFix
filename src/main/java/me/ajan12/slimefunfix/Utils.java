package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import java.util.Arrays;
import java.util.logging.Logger;

final class Utils {

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

    private Inventory gui;
    Inventory getGUI() throws Exception {

        if (gui != null) return gui;
        Inventory inv = Bukkit.createInventory(null, 27, getGUIName());

        ItemStack iB = new CustomItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, ChatColor.AQUA + "Put your broken items here.");
        for (Integer integer : INPUT_BORDER) {
            inv.setItem(integer, iB);
        }

        ItemStack iR = new CustomItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Take your fixed items from here.");
        for (Integer integer : OUTPUT_BORDER) {
            inv.setItem(integer, iR);
        }

        ItemStack iLG = new CustomItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ");
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
        return inv;
    }

    boolean isGUI(InventoryView iv) {
        return iv.getTitle().equals(getGUIName());
    }

    String getGUIName() {
        return ChatColor.YELLOW + "[" + ChatColor.GREEN + "SFFIX" + ChatColor.YELLOW + "] " + ChatColor.RED + " Item fixing GUI.";
    }

    public Integer[] getInputSlots() {
        return INPUT_SLOTS;
    }

    public Integer[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

}
