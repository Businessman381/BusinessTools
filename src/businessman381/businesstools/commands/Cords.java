package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import businessman381.businesstools.Main;

public class Cords implements CommandExecutor, TabCompleter {
	
	private String targetDIM;
	Plugin plugin = Main.getPlugin(Main.class);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 1) {
			
			if (Bukkit.getPlayer(args[0]) != null) {
				
				Player target = Bukkit.getPlayer(args[0]);
				int targetX = target.getLocation().getBlockX();
				int targetY = target.getLocation().getBlockY();
				int targetZ = target.getLocation().getBlockZ();
				if (target.getWorld().getEnvironment().equals(World.Environment.NORMAL))
					targetDIM = "Overworld";
				else if (target.getWorld().getEnvironment().equals(World.Environment.NETHER))
					targetDIM = "Nether";
				else if (target.getWorld().getEnvironment().equals(World.Environment.THE_END))
					targetDIM = "The End";
				
				sender.sendMessage(ChatColor.GREEN + target.getName() + ":");
				sender.sendMessage(ChatColor.GRAY + "Coordinates: " + ChatColor.YELLOW + targetX + ", " + targetY + ", " + targetZ + ".");
				sender.sendMessage(ChatColor.GRAY + "Dimension: " + ChatColor.YELLOW + targetDIM + ".");
				try {
					if (plugin.getConfig().getList("notsilent-commands").contains("cords") ||
					plugin.getConfig().getList("notsilent-commands").contains("where") ||
					plugin.getConfig().getList("notsilent-commands").contains("whereis") ||
					plugin.getConfig().getList("notsilent-commands").contains("all"))
						target.sendMessage(ChatColor.GREEN + sender.getName() + ChatColor.GRAY + " checked your coordinates.");
				} catch (NullPointerException ex) {}
				
			} else {
				
				sendInvalidPlayer(sender);
				
			}
			
		} else {
			
			sendInvalid(sender);
			
		}
		
		return false;
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/cords [player]");
	  
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
