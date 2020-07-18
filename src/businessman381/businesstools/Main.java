package businessman381.businesstools;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import businessman381.businesstools.commands.Feed;
import businessman381.businesstools.commands.Fly;
import businessman381.businesstools.commands.Freeze;
import businessman381.businesstools.commands.Fullbright;
import businessman381.businesstools.commands.Gamemode;
import businessman381.businesstools.commands.God;
import businessman381.businesstools.commands.GodFake;
import businessman381.businesstools.commands.GodFood;
import businessman381.businesstools.commands.Heal;
import businessman381.businesstools.commands.InfBlocks;
import businessman381.businesstools.commands.PvP;
import businessman381.businesstools.commands.Restore;
import businessman381.businesstools.listeners.PingListener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		if (this.getConfig().getBoolean("enable-cmd-gamemode")) {
			getCommand("gamemode").setExecutor(new Gamemode());
			getCommand("gamemode").setTabCompleter(new Gamemode());;
		}
		
		if (this.getConfig().getBoolean("enable-cmd-feed")) {
			getCommand("feed").setExecutor(new Feed());
			getCommand("feed").setTabCompleter(new Feed());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-heal")) {
			getCommand("heal").setExecutor(new Heal());
			getCommand("heal").setTabCompleter(new Heal());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-fly")) {
			getCommand("fly").setExecutor(new Fly());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-god")) {
			God.god = new HashSet<>();
			Bukkit.getPluginManager().registerEvents(new God(), this);
			getCommand("god").setExecutor(new God());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-godfood")) {
			GodFood.godFood = new HashSet<>();
			Bukkit.getPluginManager().registerEvents(new GodFood(), this);
			getCommand("godfood").setExecutor(new GodFood());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-godfake")) {
			GodFake.godFake = new HashSet<>();
			Bukkit.getPluginManager().registerEvents(new GodFake(), this);
			getCommand("godfake").setExecutor(new GodFake());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-pvp")) {
			getCommand("pvp").setExecutor(new PvP());
			getCommand("pvp").setTabCompleter(new PvP());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-infblocks")) {
			InfBlocks.infBlocks = new HashSet<>();
			InfBlocks.blocks = new HashMap<>();
			Bukkit.getPluginManager().registerEvents(new InfBlocks(), this);
			getCommand("infblocks").setExecutor(new InfBlocks());
			getCommand("infblocks").setTabCompleter(new InfBlocks());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-fullbright")) {
			getCommand("fullbright").setExecutor(new Fullbright());
			getCommand("fullbright").setTabCompleter(new Fullbright());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-freeze")) {
			Freeze.freezed = new HashSet<>();
			Bukkit.getPluginManager().registerEvents(new Freeze(), this);
			getCommand("freeze").setExecutor(new Freeze());
		}
		
		if (this.getConfig().getBoolean("enable-cmd-restore")) {
			getCommand("restore").setExecutor(new Restore());
			getCommand("restore").setTabCompleter(new Restore());
		}
		
		if (this.getConfig().getBoolean("enable-pinglistener")) {
			Bukkit.getPluginManager().registerEvents(new PingListener(), this);
		}
		
		System.out.println("BusinessTools have been successfully loaded.");
		if (this.getConfig().getString("config-version").equals(this.getDescription().getVersion()) == false) {
			System.out.println(ChatColor.RED + "BusinessTools config is up to date! Please delete it and reload server.");
		}
	}
	
}
