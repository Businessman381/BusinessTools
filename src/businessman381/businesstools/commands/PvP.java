package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class PvP implements CommandExecutor, TabCompleter {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("on")) {
					
					for (World world : Bukkit.getWorlds()) world.setPVP(true);
					p.sendMessage(ChatColor.GRAY + "You have turned " + ChatColor.YELLOW + args[0] + ChatColor.GRAY + " the PvP mode.");
					for (Player target : Bukkit.getOnlinePlayers()) {
						if (target == p) continue;
						target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has turned " + ChatColor.YELLOW + args[0] + " the PvP mode.");
					}
					
				} else if (args[0].equalsIgnoreCase("off")) {
					
					for (World world : Bukkit.getWorlds()) world.setPVP(false);
					p.sendMessage(ChatColor.GRAY + "You have turned " + ChatColor.YELLOW + args[0] + ChatColor.GRAY + " the PvP mode.");
					for (Player target : Bukkit.getOnlinePlayers()) {
						if (target == p) continue;
						target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " has turned " + ChatColor.YELLOW + args[0] + " the PvP mode.");
					}
					
				} else {
					
					sendInvalid(sender);
				
				}
				
			} else {
				
				sendInvalid(sender);
				
			}
			
		}
		
		return false;
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/pvp [on/off]");
	  
	}
	
	@Override
	public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		ArrayList<String> l = new ArrayList<String>();
		if (args.length == 1) {
			l.clear();
			l.add("on");
			l.add("off");
		}
		return l;
	}
	
}
