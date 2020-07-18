package businessman381.businesstools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;

public class Fly implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 0) {
			
			Player p = (Player) sender;
			
			if (p.getAllowFlight() == false) {
				
				p.setAllowFlight(true);
				p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " flight.");
				
			} else if (p.getAllowFlight() == true) {
				
				p.setAllowFlight(false);
				p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " flight.");
				
			}
			
		} else if (args.length == 1) {
			
			if (Bukkit.getPlayer(args[0]) != null) {
				
				Player target = Bukkit.getPlayer(args[0]);
				Player p = (Player) sender;
				
				if (target.getAllowFlight() == false) {
					
					target.setAllowFlight(true);
					p.sendMessage(ChatColor.GRAY + "You turned on the " + ChatColor.YELLOW + "flight.");
					
				} else if (target.getAllowFlight() == true) {
					
					target.setAllowFlight(false);
					p.sendMessage(ChatColor.GRAY + "You turned off the " + ChatColor.YELLOW + "flight.");
					
				}
				
			} else {
				
				sendInvalidPlayer(sender);
				
			}
			
		} else {
			
			sendInvalid(sender);
			
		}
		
		return false;
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/fly");
	    sender.sendMessage(ChatColor.GRAY + "/fly [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
}
