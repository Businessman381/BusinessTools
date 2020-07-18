package businessman381.businesstools.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class InfBlocks implements CommandExecutor, TabCompleter, Listener {
	
	public static Set<UUID> infBlocks;
	public static HashMap<Player, Material> blocks;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 1) {
				
				Player p = (Player) sender;
				Material block = Material.getMaterial(args[0].toUpperCase());
				
				if (block != null) {
					
					if (block.isBlock() == true) {
						
						ItemStack stack = new ItemStack(block, block.getMaxStackSize());
						
						if (p.getInventory().contains(block) == false) {
							
							if (InfBlocks.infBlocks.contains(p.getUniqueId()) == false) {
								
								InfBlocks.infBlocks.add(p.getUniqueId());
								InfBlocks.blocks.put(p, block);
								p.getInventory().addItem(stack);
								p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "enabled" + ChatColor.GRAY + " infinite " + ChatColor.BLUE + args[0] + ".");
								
							}
							
						} else if (p.getInventory().contains(block) == true) {
							
							if (InfBlocks.infBlocks.contains(p.getUniqueId()) == true) {
								
								InfBlocks.infBlocks.remove(p.getUniqueId());
								InfBlocks.blocks.remove(p, block);
								p.getInventory().remove(block);
								p.sendMessage(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "disabled" + ChatColor.GRAY + " infinite " + ChatColor.BLUE + args[0] + ".");
								
							}
							
						}
						
					} else {
						
						p.sendMessage(ChatColor.RED + "This is not a block.");
						
					}
					
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
	public void onPlace(BlockPlaceEvent event) {
		
		if (infBlocks.contains(event.getPlayer().getUniqueId())) {
			
			Material block = event.getBlock().getType();
			ItemStack stack = new ItemStack(block, block.getMaxStackSize());
			int slot = event.getPlayer().getInventory().getHeldItemSlot();
			
			if (block.isBlock() == true) {
				
				if (InfBlocks.blocks.get(event.getPlayer()) == block) {
					
					event.getPlayer().getInventory().setItem(slot, stack);
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		
		if (infBlocks.contains(event.getPlayer().getUniqueId())) {
			
			Material block = event.getItemDrop().getItemStack().getType();
			
			if (block.isBlock() == true) {
				
				if (InfBlocks.blocks.get(event.getPlayer()) == block) {
					
					event.setCancelled(true);
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		if (infBlocks.contains(event.getPlayer().getUniqueId())) {
			
			Material block = event.getBlock().getType();
			
			if (block.isBlock() == true) {
				
				if (InfBlocks.blocks.get(event.getPlayer()) == block) {
					
					event.setDropItems(false);
					
				}
				
			}
			
		}
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/infblocks [block]");
	  
	}
	
	@Override
	public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		ArrayList<String> l = new ArrayList<String>();
		if (args.length == 1) {
			l.clear();
			l.add("[blockname]");
		}
		return l;
	}
	
}
