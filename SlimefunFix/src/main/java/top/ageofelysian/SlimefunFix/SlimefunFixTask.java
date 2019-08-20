package top.ageofelysian.slimefunfix;

import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SlimefunFixTask implements Runnable{

    private HumanEntity clicker;
    private Inventory inv;

    SlimefunFixTask(HumanEntity clicker, Inventory inv) {
        this.clicker = clicker;
        this.inv = inv;
    }

    @Override
    public void run() {
        if (clicker instanceof Player) {
            String inventoryName = ChatColor.RED + "Put your broken sf items inside.";
            if (!inv.getName().equals(inventoryName)) return;

            int i = 0;
            while (i < inv.getSize()) {
                if (inv.getItem(i) == null) {
                    i++;
                    continue;
                }
                if (inv.getItem(i).getType() == null || inv.getItem(i).getType() == Material.AIR) {
                    i++;
                    continue;
                }

                ItemStack itemStack = inv.getItem(i);
                String texture = CustomSkull.getTexture(itemStack);

                if (itemStack.getType() == Material.SKULL_ITEM) {
                    for (ItemList item : ItemList.values()) {
                        if (item.getTextures().contains(texture)) {

                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sf give " + clicker.getName() + " " + item.name().toUpperCase() + " " + itemStack.getAmount());
                            Bukkit.getLogger().info("[SlimefunFix] Giving player " + clicker.getName() + ", " + itemStack.getAmount() + " amount of " + item.name());

                            inv.remove(itemStack);
                        }
                    }
                } else if (itemStack.getType() == Material.ENCHANTMENT_TABLE) {
                    if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Auto Enchanter")) {

                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sf give " + clicker.getName() + " AUTO_ENCHANTER " + itemStack.getAmount());
                        Bukkit.getLogger().info("[SlimefunFix] Giving player " + clicker.getName() + ", " + itemStack.getAmount() + " amount of Auto Enchanter");

                        inv.remove(itemStack);

                    } else if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Auto Disenchanter")) {

                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sf give " + clicker.getName() + " AUTO_DISENCHANTER " + itemStack.getAmount());
                        Bukkit.getLogger().info("[SlimefunFix] Giving player " + clicker.getName() + ", " + itemStack.getAmount() + " amount of Auto Disenchanter");

                        inv.remove(itemStack);

                    }
                }
                i++;
            }
        }
    }
}