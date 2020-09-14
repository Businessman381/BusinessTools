package businessman381.businesstools.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AntiDespawn implements Listener {
	
	public static HashMap<Player, Integer> customData;
	private int i = 0;
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		
		if (event.getEntity() instanceof Player) {
			
			Player p = event.getEntity();
			
			customData.put(p, i);
			for (ItemStack item : event.getDrops()) {
				ItemMeta meta = item.getItemMeta();
				meta.setCustomModelData(i);
				item.setItemMeta(meta);
			}
			i++;
			
		}
		
	}
	
	@EventHandler
	public void onDespawn(ItemDespawnEvent event) {
		
		ItemStack item = event.getEntity().getItemStack();
		
		if (item.hasItemMeta()) {
			
			if (item.getItemMeta().hasCustomModelData()) {
				
				int customDataValue = item.getItemMeta().getCustomModelData();
				
				if (customData.containsValue(customDataValue)) {
					
					event.setCancelled(true);
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onPickUp(EntityPickupItemEvent event) {
		
		if (event.getEntity() instanceof Player) {
			
			Player p = (Player) event.getEntity();
			ItemStack item = event.getItem().getItemStack();
			ItemMeta meta = item.getItemMeta();
			
			if (event.getItem().getItemStack().hasItemMeta()) {
				
				if (item.getItemMeta().hasCustomModelData()) {
					
					int customDataValue = item.getItemMeta().getCustomModelData();
					
					if (customData.containsKey(p)) {
						
						if (customData.get(p) == customDataValue) {
							
							customData.remove(p);
							meta.setCustomModelData(null);
							item.setItemMeta(meta);
							i--;
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
