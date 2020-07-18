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

public class Freeze implements CommandExecutor, Listener {
	
	public static Set<UUID> freezed;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 1) {
				
				Player p = (Player) sender;
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if (target != null) {
					
					if (freezed.contains(target.getUniqueId()) == false) {
						
						freezed.add(target.getUniqueId());
						p.sendMessage(ChatColor.GRAY + "You had" + ChatColor.YELLOW + " freeze " + ChatColor.GREEN + target.getName() + ".");
						
					} else if (freezed.contains(target.getUniqueId()) == true) {
						
						freezed.remove(target.getUniqueId());
						p.sendMessage(ChatColor.GRAY + "You had" + ChatColor.YELLOW + " unfreeze " + ChatColor.GREEN + target.getName() + ".");
						
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
