package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import businessman381.businesstools.Main;

public class Restore implements CommandExecutor, TabCompleter {
	
	Plugin plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 0) {
				
				Player p = (Player) sender;
				
				p.setFoodLevel(20);
				p.setHealth(20);
				p.sendMessage(ChatColor.GRAY + "Your health and hunger have been restrored.");
				
			} else if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("@a")) {
					
					Player p = (Player) sender;
					for (Player target : Bukkit.getOnlinePlayers()) {
						target.setFoodLevel(20);
						target.setHealth(20);
					}
					p.sendMessage(ChatColor.GRAY + "You restored health and hunger of " + ChatColor.GREEN + "all players.");
					
				} else if (Bukkit.getPlayer(args[0]) != null)	{
					
					Player p = (Player) sender;
					Player target = Bukkit.getPlayer(args[0]);
					target.setFoodLevel(20);
					target.setHealth(20);
					p.sendMessage(ChatColor.GREEN + target.getName() + "'s" + ChatColor.GRAY + " health and hunger has been restrored.");
					try {
						if (plugin.getConfig().getList("notsilent-commands").contains("restore") ||
								plugin.getConfig().getList("notsilent-commands").contains("all"))
									target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " restored you.");
					} catch (NullPointerException ex) {}
					
				} else {
					
					sendInvalidPlayer(sender);
					
				}
				
			} else {
				
				sendInvalid(sender);
				
			}
			
		}
		
		return false;
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/restore");
	    sender.sendMessage(ChatColor.GRAY + "/restore [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
	public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		ArrayList<String> l = new ArrayList<String>();
		if (args.length == 1) {
			l.clear();
			for (Player p : Bukkit.getOnlinePlayers()) {
				l.add(p.getName());
			}
		}
		return l;
	}
	
}
