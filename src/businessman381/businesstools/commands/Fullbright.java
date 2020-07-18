package businessman381.businesstools.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class Fullbright implements CommandExecutor, TabCompleter {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("on")) {
					
					p.setResourcePack("https://drive.google.com/u/0/uc?id=1ht9aSvUGH_HFn6Rczf4gqA5kRvJnk4MF&export=download");
					
				} else if (args[0].equalsIgnoreCase("off")) {
					
					p.setResourcePack("https://drive.google.com/u/0/uc?id=131J6jOvxm1LgdL5HLl_7qGnexWNCc0pB&export=download");
					
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
	    sender.sendMessage(ChatColor.GRAY + "/fullbright [on/off]");
	  
	}
	
	@Override
	public ArrayList<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		ArrayList<String> l = new ArrayList<String>();
		if (args.length == 1) {
			l.add("on");
			l.add("off");
		}
		return l;
	}
	
}
