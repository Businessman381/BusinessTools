package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import businessman381.businesstools.Main;

public class Stats implements CommandExecutor, TabCompleter {
	
	Plugin plugin = Main.getPlugin(Main.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 0) {
				
				sendPlayerStats(sender);
				
			} else if (args.length == 1) {
				
				if (Bukkit.getPlayer(args[0]) != null) {
					
					sendTargetStats(sender, args);
					
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
	
	public void sendPlayerStats(CommandSender sender) {
		
		Player p = (Player) sender;
		
		int deaths = p.getStatistic(Statistic.DEATHS);
		int ticksPlayed = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
		int sPlayed = ticksPlayed / 20 % 60;
		int mPlayed = ticksPlayed / 20 / 60 % 60;
		int hPlayed = ticksPlayed / 20 / 60 / 60 % 24;
		int dPlayed = ticksPlayed / 20 / 60 / 60 / 24;
		
		p.sendMessage(ChatColor.GRAY + "Deaths: " + ChatColor.YELLOW + deaths + " deaths.");
		p.sendMessage(ChatColor.GRAY + "Time played: " + ChatColor.YELLOW + dPlayed + "d, " + hPlayed + "h, " + mPlayed + "m, " + sPlayed + "s.");
		p.sendMessage(ChatColor.GRAY + "Damage taken: " + ChatColor.YELLOW + (p.getStatistic(Statistic.DAMAGE_TAKEN) / 20) + " hearts.");
		p.sendMessage(ChatColor.GRAY + "Damage dealt: " + ChatColor.YELLOW + (p.getStatistic(Statistic.DAMAGE_DEALT) / 20) + " hearts.");
		p.sendMessage(ChatColor.GRAY + "Player kills: " + ChatColor.YELLOW + p.getStatistic(Statistic.PLAYER_KILLS) + " kills.");
		p.sendMessage(ChatColor.GRAY + "Jumps: " + ChatColor.YELLOW + p.getStatistic(Statistic.JUMP) + " times.");
		
	}
	
	public void sendTargetStats(CommandSender sender, String[] args) {
		
		Player p = (Player) sender;
		Player target = Bukkit.getPlayer(args[0]);
		
		int deaths = target.getStatistic(Statistic.DEATHS);
		int ticksPlayed = target.getStatistic(Statistic.PLAY_ONE_MINUTE);
		int sPlayed = ticksPlayed / 20 % 60;
		int mPlayed = ticksPlayed / 20 / 60 % 60;
		int hPlayed = ticksPlayed / 20 / 60 / 60 % 24;
		int dPlayed = ticksPlayed / 20 / 60 / 60 / 24;
		
		p.sendMessage(ChatColor.GRAY + "Deaths count: " + ChatColor.YELLOW + deaths + " deaths.");
		p.sendMessage(ChatColor.GRAY + "Time played: " + ChatColor.YELLOW + dPlayed + "d, " + hPlayed + "h, " + mPlayed + "m, " + sPlayed + "s.");
		p.sendMessage(ChatColor.GRAY + "Damage taken: " + ChatColor.YELLOW + (target.getStatistic(Statistic.DAMAGE_TAKEN) / 20) + " hearts.");
		p.sendMessage(ChatColor.GRAY + "Damage dealt: " + ChatColor.YELLOW + (target.getStatistic(Statistic.DAMAGE_DEALT) / 20) + " hearts.");
		p.sendMessage(ChatColor.GRAY + "Player kills: " + ChatColor.YELLOW + target.getStatistic(Statistic.PLAYER_KILLS) + " kills.");
		p.sendMessage(ChatColor.GRAY + "Jumps: " + ChatColor.YELLOW + target.getStatistic(Statistic.JUMP) + " times.");
		try {
			if (plugin.getConfig().getList("notsilent-commands").contains("stats") ||
					plugin.getConfig().getList("notsilent-commands").contains("all"))
						target.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.GRAY + " checked your stats.");
		} catch (NullPointerException ex) {}
		
	}
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/stats");
	    sender.sendMessage(ChatColor.GRAY + "/stats [player]");
	  
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
