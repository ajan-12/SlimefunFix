package me.ajan12.slimefunfix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

class SlimefunFixCommands implements CommandExecutor {

    SlimefunFixCommands() {
        PluginCommand command = Bukkit.getPluginCommand("slimefunfix");
        command.setDescription(Messages.Message.COMMAND_DESCRIPTION.getMessage());
        command.setExecutor(this);
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
                                p.sendMessage(Messages.Message.COMMAND_FAIL_1.getMessage());
                                p.sendMessage(Messages.Message.COMMAND_FAIL_2.getMessage());
                            } else p.openInventory(gui);
                        } else p.sendMessage(Messages.Message.PERMISSION_FAIL.getMessage());
                        return true;

                    case "reload":
                        if (p.hasPermission("slimefunfix.reload")) {
                            p.sendMessage(Messages.Message.COMMAND_RELOAD.getMessage());
                            utils.reload();
                        } else p.sendMessage(Messages.Message.PERMISSION_FAIL.getMessage());
                        return true;
                }
            }

            p.sendMessage(Messages.Message.COMMAND_HELP_1.getMessage());
            p.sendMessage(Messages.Message.COMMAND_HELP_2.getMessage());
            return true;

        } else {
            if (args.length == 1 && args[0].equals("reload")) {
                utils.info(ChatColor.stripColor(Messages.Message.COMMAND_RELOAD.getMessage()));
                utils.reload();
                return true;
            }

            utils.info(ChatColor.stripColor(Messages.Message.COMMAND_HELP_1.getMessage()));
            utils.info(ChatColor.stripColor(Messages.Message.COMMAND_HELP_2.getMessage()));
            return true;
        }
    }
}
