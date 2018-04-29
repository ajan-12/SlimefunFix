package top.ageofelysian.SlimefunFix;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class SlimefunFix extends JavaPlugin {
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sffix")) { // If the player typed /sffix then do the following
			if (sender instanceof Player) {
		        Player player = (Player) sender;
		        PlayerInventory inventory = player.getInventory();
		        //List of items to fix
		        ItemStack $itemname = new ItemStack(Material.$item, 64);
		        //the thing that will happen to the inventories
		        if (inventory.contains($itemname)) {
		        	
		        }
		    }
			return true;
		}
		return false; 
	}
	
}