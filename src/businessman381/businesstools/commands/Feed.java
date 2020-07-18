package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor, TabCompleter {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (cmd.getName().equals("feed")) {
				
				if (args.length == 0) {
					
					Player player = (Player) sender;
					
					player.setFoodLevel(20);
					player.sendMessage(ChatColor.GRAY + "Your hunger has been restrored.");
					
				} else if (args.length == 1) {
					
					if (args[0].equalsIgnoreCase("@a")) {
						
						Player p = (Player) sender;
						for (Player target : Bukkit.getOnlinePlayers())
							target.setFoodLevel(20);
						p.sendMessage(ChatColor.GRAY + "You restored hunger of " + ChatColor.GREEN + "all players.");
						
					} else {
						
						if (Bukkit.getPlayer(args[0]) != null) {
							
							Player p = (Player) sender;
							Player target = Bukkit.getPlayer(args[0]);
							
							target.setFoodLevel(20);
							p.sendMessage(ChatColor.GREEN + target.getName() + "'s" + ChatColor.GRAY + " hunger has been restrored.");
							
						} else {
							
							sendInvalidPlayer(sender);
							
						}
						
					}
					
				} else {
					
					sendInvalid(sender);
					
				}
				
			}
		
		} else {
			
			System.out.println("You can't use this command through console!");
			
		}
	
		return false;
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/feed");
	    sender.sendMessage(ChatColor.GRAY + "/feed [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
	@Override
	public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		ArrayList<String> l = new ArrayList<String>();
		if (args.length == 1) {
			l.clear();
			l.add("@a");
			for (Player p : Bukkit.getOnlinePlayers()) {
				l.add(p.getName());
			}
		}
		return l;
	}
}
