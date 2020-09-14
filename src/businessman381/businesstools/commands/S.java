package businessman381.businesstools.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import businessman381.businesstools.Main;

public class S implements CommandExecutor, TabCompleter {
	
	List<?> nonsilent = Main.getPlugin(Main.class).getConfig().getList("nonsilent-commands");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 1) {
				
				if (Bukkit.getPlayer(args[0]) != null) {
					
					Player target = Bukkit.getPlayer(args[0]);
					target.teleport(p);
					try {
						if (nonsilent.contains("s") ||
						nonsilent.contains("all"))
									target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " teleported you.");
					} catch (NullPointerException ex) {}
					p.sendMessage(ChatColor.GRAY + "Teleported " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + " to you.");
					
				} else if (args[0].equals("@a")) {
					
					for(Player online : Bukkit.getOnlinePlayers()) {
						
						online.teleport(p);
						try {
							if (nonsilent.contains("s") ||
							nonsilent.contains("all"))
										online.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " teleported you.");
						} catch (NullPointerException ex) {}
						
					}
					p.sendMessage(ChatColor.GRAY + "Teleported " + ChatColor.GREEN + "all " + ChatColor.GRAY + "to you.");
					
				} else {
					
					sendInvalidPlayer(sender);
					
				}
				
			} else {
				
				sendInvalid(sender);
				
			}
			
		} else {
			
			System.out.println("You can't use this command through console!");
			
		}
		
		return false;
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/tpall [x] [y] [z]");
	    sender.sendMessage(ChatColor.GRAY + "/tpall [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
	@Override
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
