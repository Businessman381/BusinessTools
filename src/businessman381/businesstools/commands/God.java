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
import org.bukkit.plugin.Plugin;

import businessman381.businesstools.Main;
import net.md_5.bungee.api.ChatColor;

public class God implements CommandExecutor, Listener {
	
	public static Set<UUID> god;
	Plugin plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 0) {
				
				Player p = (Player) sender;
				
				if (god.contains(p.getUniqueId()) == false) {
					
					god.add(p.getUniqueId());
					p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " God Mode.");
					
				} else if (god.contains(p.getUniqueId()) == true) {
					
					god.remove(p.getUniqueId());
					p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " God Mode.");
					
				}
				
			} else if (args.length == 1) {
				
				if (Bukkit.getPlayer(args[0]) != null) {
					
					Player p = (Player) sender;
					Player target = Bukkit.getPlayer(args[0]);
					
					if (god.contains(target.getUniqueId()) == false) {
						
						god.add(target.getUniqueId());
						p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " God Mode for " + ChatColor.GREEN + target.getName() + ".");
						try {
							if (plugin.getConfig().getList("notsilent-commands").contains("god") ||
									plugin.getConfig().getList("notsilent-commands").contains("all"))
										target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " enabled your God Mode.");
						} catch (NullPointerException ex) {}
						
					} else if (god.contains(target.getUniqueId()) == true) {
						
						god.remove(target.getUniqueId());
						p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " God Mode for " + ChatColor.GREEN + target.getName() + ".");
						try {
							if (plugin.getConfig().getList("notsilent-commands").contains("god") ||
									plugin.getConfig().getList("notsilent-commands").contains("all"))
										target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " disabled your God Mode.");
						} catch (NullPointerException ex) {}
						
					}
					
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
	
	@EventHandler
	public void godDamage(EntityDamageEvent event) {
		
		Entity p = event.getEntity();
		
		if (p instanceof Player) {
			
			if (god.contains(p.getUniqueId()) == true) {
				
				event.setCancelled(true);
				
			}
			
		}
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/god");
	    sender.sendMessage(ChatColor.GRAY + "/god [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
}
