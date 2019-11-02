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
        info(Messages.Message.RELOAD_BEGIN.getMessage());
        LOGGER = null;

        Config config = new Config(SlimefunFix.getInstance());
        config.reload();
        Messages.setupMessages(config);
        setupGUI(config);

        info(Messages.Message.RELOAD_SUCCESS.getMessage());
    }

    void reset() {
        gui = null;
        guiName = null;
    }

    private Logger LOGGER = null;

    void info(String... messages) {
        if (LOGGER == null) LOGGER = SlimefunFix.getInstance().getLogger();
        Arrays.stream(messages).forEachOrdered(LOGGER::info);
    }

    void warn(String... messages) {
        if (LOGGER == null) LOGGER = SlimefunFix.getInstance().getLogger();
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
            guiName = ChatColor.translateAlternateColorCodes('&', guiName);

            final Inventory inv = Bukkit.createInventory(null, 27, guiName);

            final ItemStack iB = new CustomItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, Messages.Message.GUI_INPUT.getMessage());
            for (Integer integer : INPUT_BORDER) {
                inv.setItem(integer, iB);
            }

            final ItemStack iR = new CustomItem(Material.RED_STAINED_GLASS_PANE, Messages.Message.GUI_OUTPUT.getMessage());
            for (Integer integer : OUTPUT_BORDER) {
                inv.setItem(integer, iR);
            }

            final ItemStack iLG = new CustomItem(Material.GREEN_STAINED_GLASS_PANE, " ");
            for (Integer integer : OTHER_SLOTS) {
                inv.setItem(integer, iLG);
            }

            final ItemStack iG = new CustomItem(Material.GREEN_STAINED_GLASS_PANE, Messages.Message.GUI_HELP_NAME.getMessage(),
                    Messages.Message.GUI_HELP_LORE_1.getMessage(), Messages.Message.GUI_HELP_LORE_2.getMessage());
            for (Integer integer : HELP_SLOT) {
                inv.setItem(integer, iG);
            }

            final ItemStack iH = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUwM2NiN2VkODQ1ZTdhNTA3ZjU2OWFmYzY0N2M0N2FjNDgzNzcxNDY1YzlhNjc5YTU0NTk0Yzc2YWZiYSJ9fX0="), "&eCS-CoreLib's Head");
            for (Integer integer : HEAD_SLOT) {
                inv.setItem(integer, iH);
            }

            gui = inv;

        } catch (Exception e) {
            warn(Messages.Message.GUI_ERROR.getMessage(), e.toString());
        }

        return gui != null;
    }

    String getGUIName() { return guiName; }
    Inventory getGUI() { return gui; }
    boolean isGUI(InventoryView iv) { return iv.getTitle().equals(getGUIName()); }

    private String getDefaultGUIName() {
        return ChatColor.RED + " Item fixing GUI.";
    }

    Integer[] getInputSlots() { return INPUT_SLOTS; }
    Integer[] getOutputSlots() { return OUTPUT_SLOTS; }
    Integer[][] getInvalidSlots() { return INVALID_SLOTS; }
}
