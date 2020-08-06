package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor, TabCompleter {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if (args.length >= 2) {
				
				Player reciever = Bukkit.getPlayer(args[0]);
				
				if (reciever != null) {
					
					if (reciever == player) {
						
						String message = "";
		                for (int i = 1; i < args.length; i++)
		                    message += args[i] + " ";
						
		                player.sendMessage(ChatColor.GOLD + "[Your note]: " + ChatColor.GRAY + message);
		                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 1);
						
					} else {
						
						String message = "";
		                for (int i = 1; i < args.length; i++)
		                    message += args[i] + " ";
						
		                player.sendMessage(ChatColor.GOLD + "[You >>> " + reciever.getName() + "]: " + ChatColor.GRAY + message);
		                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 1);
		                reciever.sendMessage(ChatColor.GOLD + "[" + player.getName() +  " >>> You]: " + ChatColor.GRAY + message);
		                reciever.playSound(reciever.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 100, 1);
						
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
	
	private void sendInvalid(CommandSender sender) {
		
	    sender.sendMessage(ChatColor.RED + "Invalid usage." + ChatColor.GRAY + " Please use:");
	    sender.sendMessage(ChatColor.GRAY + "/m [player] [message]");
	  
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
