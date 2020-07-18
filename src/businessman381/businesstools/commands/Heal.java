package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor, TabCompleter {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equals("heal")) {
			
			if (sender instanceof Player) {
				
				if (args.length == 0) {
					
					Player player = (Player) sender;
					player.setHealth(20);
					player.sendMessage(ChatColor.GRAY + "Your health has been restrored.");
					
				} else if (args.length == 1) {
					
					if (args[0].equals("@a")) {
						
						Player p = (Player) sender;
						for (Player target : Bukkit.getOnlinePlayers())
							target.setHealth(20);
						p.sendMessage(ChatColor.GRAY + "You restored health of " + ChatColor.GREEN + "all players.");
						
					} else {
						
						if (Bukkit.getPlayer(args[0]) != null) {
							
							Player p = (Player) sender;
							Player target = Bukkit.getPlayer(args[0]);
							target.setHealth(20);
							p.sendMessage(ChatColor.GREEN + target.getName() + "'s" + ChatColor.GRAY + " health has been restrored.");
							
						} else {
							
							sendInvalidPlayer(sender);
							
						}
						
					}
					
				} else {
					
					sendInvalid(sender);
					
				}
			
			} else {
					
				System.out.println("You can't use this command through console!");
				
			}
			
		}
	
		return false;
		
	}

	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/heal");
	    sender.sendMessage(ChatColor.GRAY + "/heal [player]");
	  
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
