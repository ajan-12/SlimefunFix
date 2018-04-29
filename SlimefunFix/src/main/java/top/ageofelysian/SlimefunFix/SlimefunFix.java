package top.ageofelysian.SlimefunFix;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimefunFix extends JavaPlugin {
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sffix")) { // If the player typed /sffix then do the following
			if (sender instanceof Player) {
		        Player player = (Player) sender;
		        PlayerInventory inventory = player.getInventory();
        //Checking if player has a skull item named CSCoreLib
        ItemStack skullcheck = new ItemStack(Material.SKULL_ITEM, 1);
        //if (OfflinePlayer.getOwningPlayer().equals("CSCoreLib")) {
              //if (inventory.contains(skullcheck)) {
		        	      
              //}
        //}
	private static String getSkullName(Skull skullcheck) {
                 skullcheck.getOwningPlayer().getName();
        }
		        }
			return true;
		}
		return false; 
	}
	
}
