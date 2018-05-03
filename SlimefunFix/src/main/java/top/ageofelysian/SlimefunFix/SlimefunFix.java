package top.ageofelysian.SlimefunFix;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;


public final class SlimefunFix extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info(ChatColor.DARK_RED + "SlimefunFix loaded.");
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
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sffix")) { // If the player typed /sffix then do the following
			// If the command has no args
			if (args.length == 0) {
				//If the sender is a player
				if (sender instanceof Player) {
					Player player = (Player) sender;
					// If the player has the permission node sffix.use
					if (player.hasPermission("sffix.use")) {
						PlayerInventory inventory = player.getInventory();
						// variable glitched item is a skull item
						ItemStack glitcheditem = new ItemStack(Material.SKULL_ITEM);
						int itemamount = glitcheditem.getAmount();
						SkullMeta skullMeta = (SkullMeta)glitcheditem.getItemMeta();
						// If the player has glitcheditem and its display name equals to CSCoreLib
						if (inventory.contains(glitcheditem) && glitcheditem.getItemMeta().getDisplayName().equals("CSCoreLib")) {
					        File file = new File(getDataFolder(), "items.yml");
					        // variable owner is the owner of the item glitcheditem
							OfflinePlayer owner = skullMeta.getOwningPlayer();
							if (getConfig().getString("debug").equals("true")) {
								player.sendMessage(ChatColor.DARK_RED + "[SfFix] DEBUG" + ChatColor.GRAY + " >" + owner);
							}
							ArrayList<String> itemowners = (ArrayList<String>)getConfig().getStringList("items.owners");
							ArrayList<String> itemnames = (ArrayList<String>)getConfig().getStringList("items.names");
							// If the arraylist itemowners has owner
							if (itemowners.contains(owner) && itemowners.indexOf(owner) == itemnames.indexOf(owner)) {
								int fixeditem = itemnames.indexOf(owner);
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sf give" + player + itemnames.get(fixeditem) + itemamount);
								player.sendMessage(ChatColor.AQUA + "Your item" + ChatColor.RED + itemnames.get(fixeditem) + ChatColor.AQUA + "is fixed.");
								glitcheditem.setAmount(0);
								return true;
							}
						}
					} else {
						player.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to perform this command.");
						return false;
					}
				} else {
					player.sendMessage(ChatColor.DARK_RED + "This command can only be applied in game!");
					return false;
				}
			}
			// If the 1st arg is "help"
			if (args[0] == "help") {
				// If the sender is a player
				if (sender instanceof Player) {
					Player player = (Player) sender;
					// If player has the permission node sffix.help
					if (player.hasPermission("sffix.help")) {
						String help1 = "/sffix to fix your items.";
						player.sendMessage(ChatColor.AQUA + help1);
						return true;
					} else {
						player.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to perform this command.");
						return false;
					}
				} else {
					player.sendMessage(ChatColor.DARK_RED + "This command can only be applied in game!");
					return false;
				}
			}
			// If the 1st arg is "reload"
			if (args[0] == "reload") {
				// If the sender is a player
				if (sender instanceof Player) {
					Player player = (Player) sender;
					// If player has the permission node sffix.reload
					if (player.hasPermission("sffix.reload")) {
						reloadConfig();
						player.sendMessage(ChatColor.DARK_GREEN + "Plugin reloaded.");
						return true;
					} else {
						player.sendMessage(ChatColor.DARK_RED + "You do not have enough permissions to perform this command.");
						return false;
					}
				} else {
					reloadConfig();
					return true;
				}
			}
		}
		
		if (cmd.getName().equalsIgnoreCase("lightningbolt")) {
			// If there is no args
			if (args.length == 0) {
				// If the sender is a player
				if (sender instanceof Player) {
					Player player = (Player) sender;
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
