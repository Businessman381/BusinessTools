package businessman381.businesstools.commands;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import net.md_5.bungee.api.ChatColor;

public class GodFake implements CommandExecutor, Listener {
	
	public static Set<UUID> godFake;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 0) {
				
				Player p = (Player) sender;
				
				if (godFake.contains(p.getUniqueId()) == false) {
					
					godFake.add(p.getUniqueId());
					p.setFoodLevel(19);
					p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " GodFake Mode.");
					
				} else if (godFake.contains(p.getUniqueId()) == true) {
					
					godFake.remove(p.getUniqueId());
					p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " GodFake Mode.");
					
				}
				
			} else if (args.length == 1) {
				
				if (Bukkit.getPlayer(args[0]) != null) {
					
					Player p = (Player) sender;
					Player target = Bukkit.getPlayer(args[0]);
					
					if (godFake.contains(target.getUniqueId()) == false) {
						
						godFake.add(target.getUniqueId());
						target.setFoodLevel(19);
						p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " GodFake Mode for " + ChatColor.GREEN + target.getName() + ".");
						
					} else if (godFake.contains(target.getUniqueId()) == true) {
						
						godFake.remove(target.getUniqueId());
						p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " GodFake Mode for " + ChatColor.GREEN + target.getName() + ".");
						
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
	public void onDamage(EntityDamageEvent event) {
		
		Entity p = event.getEntity();
		
		if (p instanceof Player) {
			
			if (godFake.contains(p.getUniqueId()) == true) {
				
				event.setDamage(0);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void foodLevel(FoodLevelChangeEvent event) {
		
		if (event.getEntity() instanceof Player) {
			
			Player p = (Player) event.getEntity();
			
			if (godFake.contains(p.getUniqueId())) {
				
				event.setCancelled(true);
			
			}
			
		}
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/godfake");
	    sender.sendMessage(ChatColor.GRAY + "/godfake [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
}
