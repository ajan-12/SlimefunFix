package top.ageofelysian.SlimefunFix;

import java.awt.List;
import java.io.File;
import java.util.Set;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.ItemMeta;


@SuppressWarnings("unused")
public final class SlimefunFix extends JavaPlugin {
	
	public void onEnable() {
		getLogger().info(ChatColor.RED + "SlimefunFix loaded.");
		try {
	        if (!getDataFolder().exists()) {
	            getDataFolder().mkdirs();
	        }
	        File file = new File(getDataFolder(), "items.yml");
	        if (!file.exists()) {
	            getLogger().info("Items.yml not found, creating!");
	            saveDefaultConfig();
	        } else {
	            getLogger().info("Items.yml found, loading!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();

	    }

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("sffix")) { // If the player typed /sffix then do the following
			// If the command has no args
			if (args.length == 0) {
				//If the sender is a player
				if (sender instanceof Player) {
					// If the player has the permission node sffix.use
					if (player.hasPermission("sffix.use")) {
						PlayerInventory inventory = player.getInventory();
						ItemStack glitcheditem = new ItemStack(Material.SKULL_ITEM, 1);
						SkullMeta skullMeta = (SkullMeta)glitcheditem.getItemMeta();
						if (inventory.contains(glitcheditem) && glitcheditem.getItemMeta().getDisplayName().equals("CSCoreLib")) {
					        File file = new File(getDataFolder(), "items.yml");
							OfflinePlayer owner = skullMeta.getOwningPlayer();
							ArrayList<String> itemlist = (ArrayList<String>)getConfig().getStringList("items.owner");
							if (itemlist.contains(owner)) {
								(ArrayList<String>)getConfig().getStringList("items.owner")
							}
						}
					} else {
					
					}
				return true;
				}
			return true;
			}
			// If there is no args
			if (args[0] == "help") {
				// If the sender is a player
				if (sender instanceof Player) {
					// If player has the permission node sffix.help
					if (player.hasPermission("sffix.help")) {
					
					}
				return true;
				}
			return true;
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("lightningbolt")) {
			// If there is no args
			if (args.length == 0) {
				// If the sender is a player
				if (sender instanceof Player) {
					// If the player has the permission node sffix.use
					if (player.hasPermission("sffix.lightning")) {
						// Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
						// Can only create lightning up to 200 blocks away.
						player.getWorld().strikeLightning(player.getTargetBlock((Set<Material>) null, 200).getLocation());
					}
				return true;
				}
			return true;
			}
		}
		return true;
	}
}