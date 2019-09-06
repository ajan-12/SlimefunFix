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

                    case "gui":
                        if (p.hasPermission("slimefunfix.use")) {
                            Inventory gui = utils.getGUI();
                            if (gui == null) {
                                p.sendMessage(ChatColor.DARK_RED + "An error occurred while trying to create the GUI.");
                                p.sendMessage(ChatColor.DARK_RED + "Please report this to an Administrator.");
                            } else p.openInventory(gui);
                        } else p.sendMessage(ChatColor.DARK_RED + "You do not have permission to do this.");
                        return true;

                    case "reload":
                        if (p.hasPermission("slimefunfix.reload")) {
                            p.sendMessage(ChatColor.GREEN + "Reloading the plugin.");
                            utils.reload();
                        } else p.sendMessage(ChatColor.DARK_RED + "You do not have permission to do this.");
                        return true;
                }
            }

            String pluginTag = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "SFFIX" + ChatColor.WHITE + "] ";
            p.sendMessage(pluginTag + ChatColor.YELLOW + "/slimefunfix help" + ChatColor.GREEN + " to see how to fix your broken slimefun items.");
            p.sendMessage(pluginTag + ChatColor.YELLOW + "/slimefunfix gui" + ChatColor.GREEN + " to fix your broken slimefun items.");
            return true;

        } else {
            if (args.length == 1 && args[0].equals("reload")) {
                utils.info("Reloading the plugin.");
                utils.reload();
                return true;
            }

            String pluginTag = "[SFFIX] ";
            utils.info(pluginTag + "/slimefunfix help to see how to fix your broken slimefun items.");
            utils.info(pluginTag + "/slimefunfix gui to fix your broken slimefun items.");
            return true;
        }
    }
}
