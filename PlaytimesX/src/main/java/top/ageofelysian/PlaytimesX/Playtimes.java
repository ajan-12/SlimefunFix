package top.ageofelysian.PlaytimesX;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Playtimes {

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("playtime")) { 
	  if (!(sender instanceof Player)) {
	    sender.sendMessage("This command can only be run by a player.");
	    } else {
        int a = getStatistic(Statistic.PLAY_ONE_TICK);
	    sender.sendMessage("Your current playtime is:" + a + "ticks" + (a/20) + "seconds" + (a/20/60) + "minutes" + (a/20/60/24) + "days" + (a/20/60/24/30) + "months" + (a/20/60/24/30/12) + "years.");
		return true;
	    }
      }
	return false;
  }

  private int getStatistic(Statistic playOneTick) {
    return 0;
  }
}
