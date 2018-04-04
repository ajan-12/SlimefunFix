package com.playtimesx;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Playtimes {
  
  	@Override
	public void load() {
		PlaytimesX.getInstance().getCommand("playtime").setExecutor(this);
		PlaytimesX.getInstance().getCommand("playtime").setDescription("Shows the player's playtime informations.'");
		PlaytimesX.getInstance().getCommand("playtime").setUsage("To see your own playtime (/playtime) or to see someone else''s playtime information(/playtime <playername>).");
		
		   @Override
		 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		  if (cmd.getName().equalsIgnoreCase("playtime")) { 
		    if (!(sender instanceof Player)) {
		     	sender.sendMessage("This command can only be run by a player.");
		     	} else {
		         int a = getStatistic(Statistic.PLAY_ONE_TICK)
		           CommandSender.sendMessage("Your current playtime is:" + a + "ticks" + (a÷20) + "seconds" + (a÷20÷60) + "minutes" + (a÷20÷60÷24) + "days" + (a÷20÷60÷24÷30) + "months" + (a÷20÷60÷24÷30÷12) + "years.");
		  	       	   return true;
		  	     }
    }
		  return false;
	  }
	}
}
