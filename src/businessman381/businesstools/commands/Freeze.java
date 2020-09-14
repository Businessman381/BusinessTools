package businessman381.businesstools.commands;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import businessman381.businesstools.Main;

public class Freeze implements CommandExecutor, Listener {
	
	public static Set<UUID> freezed;
	Plugin plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 1) {
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if (target != null) {
				
				if (freezed.contains(target.getUniqueId()) == false) {
					
					freezed.add(target.getUniqueId());
					sender.sendMessage(ChatColor.GRAY + "You had" + ChatColor.YELLOW + " freeze " + ChatColor.GREEN + target.getName() + ".");
					try {
						if (plugin.getConfig().getList("notsilent-commands").contains("freeze") ||
								plugin.getConfig().getList("notsilent-commands").contains("all"))
									target.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " freezed you.");
					} catch (NullPointerException ex) {}
					
				} else if (freezed.contains(target.getUniqueId()) == true) {
					
					freezed.remove(target.getUniqueId());
					sender.sendMessage(ChatColor.GRAY + "You had" + ChatColor.YELLOW + " unfreeze " + ChatColor.GREEN + target.getName() + ".");
					try {
						if (plugin.getConfig().getList("notsilent-commands").contains("freeze") ||
								plugin.getConfig().getList("notsilent-commands").contains("all"))
									target.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " unfreezed you.");
					} catch (NullPointerException ex) {}
					
				}
				
			} else {
				
				sendInvalidPlayer(sender);
				
			}
			
		} else {
			
			sendInvalid(sender);
			
		}
		
		return false;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		
		Player p = event.getPlayer();
		
		if (freezed.contains(p.getUniqueId())) {
			
			if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                Location loc = event.getFrom();
                p.teleport(loc.setDirection(event.getTo().getDirection()));
            }			
		}
		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		
		if (freezed.contains(p.getUniqueId())) {
			
			event.setCancelled(true);
			
		}
		
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event) {
		
		if (event.getDamager() instanceof Player) {
			
			if (freezed.contains(event.getDamager().getUniqueId())) {
				
				event.setCancelled(true);
				
			}
			
		}
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/freeze [player]");
	  
	}
	
	private void sendInvalidPlayer(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED + "There is no such player on the server.");
		
	}
	
}
