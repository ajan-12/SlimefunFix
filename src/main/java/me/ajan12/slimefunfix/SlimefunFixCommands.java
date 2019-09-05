package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

class SlimefunFixCommands implements CommandExecutor {

    SlimefunFixCommands() {
        Bukkit.getPluginCommand("slimefunfix").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utils utils = new Utils();

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1) {
                switch (args[0]) {

                    case "help":
                        String pluginTag = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "SFFIX" + ChatColor.WHITE + "] ";
                        p.sendMessage(pluginTag + ChatColor.GREEN + "Put the broken Slimefun head blocks that has a texture inside and your fixed items will appear in your inventory or will be dropped. Any undefined item will not be touched. When you close the gui the items that were inside may get lost.");
                        p.sendMessage(pluginTag + ChatColor.GREEN + "Please note that the broken items that may have the same texture wth another sf machine (f.e capacitors and gps transmitters) will give you the lowest possible item.");
                        return true;

                    case "gui":
                        Inventory inventory = Bukkit.createInventory(null, 27, utils.getGUIName());
                        p.openInventory(inventory);
                        return true;
                }

            } else if (args.length == 0){
                String pluginTag = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "SFFIX" + ChatColor.WHITE + "] ";
                p.sendMessage(pluginTag + ChatColor.YELLOW + "/slimefunfix help " + ChatColor.GREEN + " to see how to fix your broken slimefun items.");
                p.sendMessage(pluginTag + ChatColor.YELLOW + "/slimefunfix gui " + ChatColor.GREEN + " to fix your broken slimefun items.");
                return true;
            }
        } else {
            sender.sendMessage("This command can only be applied in-game!");
            return true;
        }
        return false;
    }
}
