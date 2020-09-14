package businessman381.businesstools.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import businessman381.businesstools.Main;

public class TpAll implements CommandExecutor, TabCompleter {
	
	List<?> nonsilent = Main.getPlugin(Main.class).getConfig().getList("nonsilent-commands");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				
				for (Player online : Bukkit.getOnlinePlayers()) {
					if (online == p) continue;
					else {
						online.teleport(p);
						try {
							if (nonsilent.contains("tpall") ||
							nonsilent.contains("all"))
										online.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " teleported you.");
						} catch (NullPointerException ex) {}
					}
					
				}
				p.sendMessage(ChatColor.GRAY + "Teleported all to " + ChatColor.YELLOW + "you.");
				
			} else if (args.length == 1) {
				
				if (Bukkit.getPlayer(args[0]) != null) {
					
					Player target = Bukkit.getPlayer(args[0]);
					for (Player online : Bukkit.getOnlinePlayers())
						if (online == target) continue;
						else online.teleport(target);
					p.sendMessage(ChatColor.GRAY + "Teleported all to " + ChatColor.YELLOW + target.getName() + ".");
					
				} else {
					
					sendInvalidPlayer(sender);
					
				}
				
			} else if (args.length == 3) {
				
				try {
					
					double x = Double.parseDouble(args[0]);
					double y = Double.parseDouble(args[1]);
					double z = Double.parseDouble(args[2]);
					
					if (args[0].equals("~")) x = p.getLocation().getX();
					if (args[1].equals("~")) y = p.getLocation().getY();
					if (args[2].equals("~")) z = p.getLocation().getZ();
					
					for (Player online : Bukkit.getOnlinePlayers())
						online.teleport(new Location(p.getWorld(), x, y, z));
					p.sendMessage(ChatColor.GRAY + "Teleported all to " + ChatColor.YELLOW + Math.round(x) + " " + Math.round(y) + " " + Math.round(z) + ".");
					
				} catch (NumberFormatException ex) {
					
					sendInvalid(sender);
					
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
			for (Player p : Bukkit.getOnlinePlayers())
				l.add(p.getName());
			if (sender instanceof Player)
				l.add(((Player) sender).getLocation().getBlockX() + "");
		} else if (args.length == 2) {
			l.clear();
			if (sender instanceof Player)
				l.add(((Player) sender).getLocation().getBlockY() + "");
		} else if (args.length == 3) {
			l.clear();
			if (sender instanceof Player)
				l.add(((Player) sender).getLocation().getBlockZ() + "");
		}
		
		return l;
	}
	
}
