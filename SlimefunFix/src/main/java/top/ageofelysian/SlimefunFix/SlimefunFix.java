package top.ageofelysian.SlimefunFix;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("unused")
public final class SlimefunFix extends JavaPlugin {
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sffix")) { // If the player typed /sffix then do the following
			if (sender instanceof Player) {
		        Player player = (Player) sender;
		        PlayerInventory inventory = player.getInventory();
		        ItemStack glitcheditem = new ItemStack(Material.SKULL_ITEM, 1);
				if (inventory.contains(glitcheditem) && glitcheditem.getItemMeta().getDisplayName().equals("CSCoreLib")) {
					
		        }
		    }
			return true;
		}
		return false; 
	}
}
