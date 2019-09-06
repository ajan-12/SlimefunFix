package me.ajan12.slimefunfix;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

class SlimefunFixTask implements Runnable {

    private HumanEntity clicker;
    private InventoryView iv;

    SlimefunFixTask(HumanEntity clicker, InventoryView iv) {
        this.clicker = clicker;
        this.iv = iv;
    }

    @Override
    public void run() {
        if (clicker instanceof Player) {
            Utils utils = new Utils();
            Inventory inv = iv.getTopInventory();

            for (Integer inputSlot : utils.getInputSlots()) {
                ItemStack itemStack = inv.getItem(inputSlot);
                if (itemStack != null && itemStack.getType() != Material.AIR) {

                    String texture = CustomSkull.getTexture(itemStack);
                    if (itemStack.getType() == Material.PLAYER_HEAD) {
                        try {
                            for (SlimefunItem SFItem : SlimefunItem.list()) {
                                if (!SFItem.isDisabled() && SFItem.getItem() != null && SFItem.getItem().getType() == Material.PLAYER_HEAD) {
                                    ItemStack SFItemStack = SFItem.getItem();
                                    if (SFItemStack.getType() == Material.PLAYER_HEAD) {
                                        String SFTexture = CustomSkull.getTexture(SFItemStack);
                                        if (SFTexture.equals(texture)) {

                                            ItemStack stack = SFItemStack.clone();
                                            stack.setAmount(itemStack.getAmount());

                                            for (Integer outputSlot : utils.getOutputSlots()) {
                                                ItemStack temp = inv.getItem(outputSlot);
                                                if (temp == null || temp.getType() == Material.AIR) {

                                                    itemStack.setAmount(0);
                                                    inv.setItem(outputSlot, stack);
                                                    utils.info("[SlimefunFix] Giving player " + clicker.getName() + ", " + stack.getAmount() + " amount of " + SFItem.getID());
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception | Error e) {
                            utils.warn("You are using an outdated version of Slimefun!");
                            return;
                        }
                    }
                }
            }
        }
    }
}