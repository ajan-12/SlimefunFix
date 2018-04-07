package top.ageofelysian.PlaytimesX;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
public class RankUp {
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("rankcheck")) {
      if (!(sender instanceof Player)) {
        sender.sendMessage("This command can only be run in game.");
      } else {
        int a;
        if (a > 14400000) {
          if (a < 32400000) {
	        
          }
        }
        if (a > 32400000) {
          if (a < 50400000) {
            
          }
        }
        if (a > 50400000) {
          if (a < 7000000) {
        	  ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        	  String command = "scoreboard players operation" + sender.getName() + "RankSymbolID Layers RankSymbolID";
        	  Bukkit.dispatchCommand(console, command);
          }
        }
        if (a > 70000000) {
          
        }
      }
    }
  return true;
  }
}
