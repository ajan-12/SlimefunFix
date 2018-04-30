package top.ageofelysian.SlimefunFix;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
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
					OfflinePlayer owner = glitcheditem.getItemMeta(getSkullMeta()).getOwningPlayer();
		        }
		    }
			return true;
		}
		return false;
		
		if (cmd.getName().equalsIgnoreCase("lightningbolt")) {
			Player player = (Player) sender;
			// Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
			// Can only create lightning up to 200 blocks away.
			player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
		}
	}
}
