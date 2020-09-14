package businessman381.businesstools.commands;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.Plugin;

import businessman381.businesstools.Main;
import net.md_5.bungee.api.ChatColor;

public class GodFood implements CommandExecutor, Listener {
	
	public static Set<UUID> godFood;
	Plugin plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 0) {
				
				Player p = (Player) sender;
				
				if (godFood.contains(p.getUniqueId()) == false) {
					
					godFood.add(p.getUniqueId());
					p.setFoodLevel(20);
					p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " GodFood Mode.");
					
				} else if (godFood.contains(p.getUniqueId()) == true) {
					
					godFood.remove(p.getUniqueId());
					p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " GodFood Mode.");
					
				}
				
			} else if (args.length == 1) {
				
				if (Bukkit.getPlayer(args[0]) != null) {
					
					Player p = (Player) sender;
					Player target = Bukkit.getPlayer(args[0]);
					
					if (godFood.contains(target.getUniqueId()) == false) {
						
						godFood.add(target.getUniqueId());
						target.setFoodLevel(20);
						p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " GodFood Mode for " + ChatColor.GREEN + target.getName() + ".");
						try {
							if (plugin.getConfig().getList("notsilent-commands").contains("godfood") ||
									plugin.getConfig().getList("notsilent-commands").contains("all"))
										target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " enabled your GodFood Mode.");
						} catch (NullPointerException ex) {}
						
					} else if (godFood.contains(target.getUniqueId()) == true) {
						
						godFood.remove(target.getUniqueId());
						p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " GodFood Mode for " + ChatColor.GREEN + target.getName() + ".");
						try {
							if (plugin.getConfig().getList("notsilent-commands").contains("godfood") ||
									plugin.getConfig().getList("notsilent-commands").contains("all"))
										target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " disabled your GodFood Mode.");
						} catch (NullPointerException ex) {}
						
					}
					
				} else {
					
					sendInvalidPlayer(sender);
					
				}
				
			} else {
				
				sendInvalid(sender);
				
			}
			
		}
		
		return false;
		
	}
	
	@EventHandler
	public void foodLevel(FoodLevelChangeEvent event) {
		
		if (event.getEntity() instanceof Player) {
			
			Player p = (Player) event.getEntity();
			
			if (godFood.contains(p.getUniqueId())) {
				
				event.setCancelled(true);
			
			}
			
		}
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/godfood");
	    sender.sendMessage(ChatColor.GRAY + "/godfood [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
}
