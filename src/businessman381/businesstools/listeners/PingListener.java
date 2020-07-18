package businessman381.businesstools.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import businessman381.businesstools.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

public class PingListener implements Listener {
	
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		
		Plugin plugin = Main.getPlugin(Main.class);
		
		e.setMaxPlayers(plugin.getConfig().getInt("max-players"));
		String icon = plugin.getConfig().getString("icon-file-name");
		String address = e.getAddress().toString();
		if (plugin.getConfig().getBoolean("enable-icon") == true) {
			try {
				e.setServerIcon(Bukkit.loadServerIcon(new File(icon)));
			} catch (IllegalArgumentException ex) {
				System.out.println(ChatColor.YELLOW + address + " tried to load server icon! Please put " + icon + " in server folder or disable it in config!");
			} catch (Exception ex) {
				
			}
		}
		
	}
	
}
