package businessman381.businesstools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import businessman381.businesstools.commands.Cords;
import businessman381.businesstools.commands.Enderchest;
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
import businessman381.businesstools.commands.Message;
import businessman381.businesstools.commands.PvP;
import businessman381.businesstools.commands.Restore;
import businessman381.businesstools.commands.S;
import businessman381.businesstools.commands.Stats;
import businessman381.businesstools.commands.TpAll;
import businessman381.businesstools.listeners.AntiDespawn;
import businessman381.businesstools.listeners.PingListener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();
		List<?> disabledcommands = this.getConfig().getList("disabled-commands");
		
		
		// COMMANDS
		try {
			if (!disabledcommands.contains("gamemode") &&
			!disabledcommands.contains("gm") &&
			!disabledcommands.contains("all")) {
				getCommand("gamemode").setExecutor(new Gamemode());
				getCommand("gamemode").setTabCompleter(new Gamemode());
			}
		} catch (NullPointerException ex) {
			getCommand("gamemode").setExecutor(new Gamemode());
			getCommand("gamemode").setTabCompleter(new Gamemode());
		}
		
		try {
			if (!disabledcommands.contains("feed") &&
			!disabledcommands.contains("food") &&
			!disabledcommands.contains("hunger") &&
			!disabledcommands.contains("all")) {
				getCommand("feed").setExecutor(new Feed());
				getCommand("feed").setTabCompleter(new Feed());
			}
		} catch (NullPointerException ex) {
			getCommand("feed").setExecutor(new Feed());
			getCommand("feed").setTabCompleter(new Feed());
		}
		
		try {
			if (!disabledcommands.contains("heal") &&
			!disabledcommands.contains("all")) {
				getCommand("heal").setExecutor(new Heal());
				getCommand("heal").setTabCompleter(new Heal());
			}
		} catch (NullPointerException ex) {
			getCommand("heal").setExecutor(new Heal());
			getCommand("heal").setTabCompleter(new Heal());
		}
		
		try {
			if (!disabledcommands.contains("fly") &&
			!disabledcommands.contains("all")) {
				getCommand("fly").setExecutor(new Fly());
			}
		} catch (NullPointerException ex) {
			getCommand("fly").setExecutor(new Fly());
		}
		
		try {
			if (!disabledcommands.contains("god") &&
			!disabledcommands.contains("all")) {
				Bukkit.getPluginManager().registerEvents(new God(), this);
				God.god = new HashSet<>();
				getCommand("god").setExecutor(new God());
			}
		} catch (NullPointerException ex) {
			Bukkit.getPluginManager().registerEvents(new God(), this);
			God.god = new HashSet<>();
			getCommand("god").setExecutor(new God());
		}
		
		try {
			if (!disabledcommands.contains("godfood") &&
			!disabledcommands.contains("all")) {
				GodFood.godFood = new HashSet<>();
				Bukkit.getPluginManager().registerEvents(new GodFood(), this);
				getCommand("godfood").setExecutor(new GodFood());
			}
		} catch (NullPointerException ex) {
			GodFood.godFood = new HashSet<>();
			Bukkit.getPluginManager().registerEvents(new GodFood(), this);
			getCommand("godfood").setExecutor(new GodFood());
		}
		
		try {
			 if (!disabledcommands.contains("godfake") &&
			!disabledcommands.contains("all")) {
				GodFake.godFake = new HashSet<>();
				Bukkit.getPluginManager().registerEvents(new GodFake(), this);
				getCommand("godfake").setExecutor(new GodFake());
			 }
		} catch (NullPointerException ex) {
			GodFake.godFake = new HashSet<>();
			Bukkit.getPluginManager().registerEvents(new GodFake(), this);
			getCommand("godfake").setExecutor(new GodFake());
		}
		
		try {
			if (!disabledcommands.contains("pvp") &&
			!disabledcommands.contains("all")) {
				getCommand("pvp").setExecutor(new PvP());
				getCommand("pvp").setTabCompleter(new PvP());
			}
		} catch (NullPointerException ex) {
			getCommand("pvp").setExecutor(new PvP());
			getCommand("pvp").setTabCompleter(new PvP());
		}
		
		try {
			if (!disabledcommands.contains("infblocks") &&
			!disabledcommands.contains("all")) {
				InfBlocks.infBlocks = new HashSet<>();
				InfBlocks.blocks = new HashMap<>();
				Bukkit.getPluginManager().registerEvents(new InfBlocks(), this);
				getCommand("infblocks").setExecutor(new InfBlocks());
				getCommand("infblocks").setTabCompleter(new InfBlocks());
			}
		} catch (NullPointerException ex) {
			InfBlocks.infBlocks = new HashSet<>();
			InfBlocks.blocks = new HashMap<>();
			Bukkit.getPluginManager().registerEvents(new InfBlocks(), this);
			getCommand("infblocks").setExecutor(new InfBlocks());
			getCommand("infblocks").setTabCompleter(new InfBlocks());
		}
		
		try {
			if (!disabledcommands.contains("fullbright") &&
			!disabledcommands.contains("all")) {
				getCommand("fullbright").setExecutor(new Fullbright());
				getCommand("fullbright").setTabCompleter(new Fullbright());
			}
		} catch (NullPointerException ex) {
			getCommand("fullbright").setExecutor(new Fullbright());
			getCommand("fullbright").setTabCompleter(new Fullbright());
		}
		
		try {
			if (!disabledcommands.contains("freeze") &&
			!disabledcommands.contains("all")) {
				Freeze.freezed = new HashSet<>();
				Bukkit.getPluginManager().registerEvents(new Freeze(), this);
				getCommand("freeze").setExecutor(new Freeze());
			}
		} catch (NullPointerException ex) {
			Freeze.freezed = new HashSet<>();
			Bukkit.getPluginManager().registerEvents(new Freeze(), this);
			getCommand("freeze").setExecutor(new Freeze());
		}
		
		try {
			if (!disabledcommands.contains("restore") &&
			!disabledcommands.contains("all")) {
				getCommand("restore").setExecutor(new Restore());
				getCommand("restore").setTabCompleter(new Restore());
			}
		} catch (NullPointerException ex) {
			getCommand("restore").setExecutor(new Restore());
			getCommand("restore").setTabCompleter(new Restore());
		}
		
		try {
			if (!disabledcommands.contains("stats") &&
			!disabledcommands.contains("all")) {
				getCommand("stats").setExecutor(new Stats());
				getCommand("stats").setTabCompleter(new Stats());
			}
		} catch (NullPointerException ex) {
			getCommand("stats").setExecutor(new Stats());
			getCommand("stats").setTabCompleter(new Stats());
		}
		
		try {
			if (!disabledcommands.contains("message") &&
			!disabledcommands.contains("m") &&
			!disabledcommands.contains("all")) {
				getCommand("message").setExecutor(new Message());
				getCommand("message").setTabCompleter(new Message());
			}
		} catch (NullPointerException ex) {
			getCommand("message").setExecutor(new Message());
			getCommand("message").setTabCompleter(new Message());
		}
		
		try {
			if (!disabledcommands.contains("enderchest") &&
			!disabledcommands.contains("all")) {
				getCommand("enderchest").setExecutor(new Enderchest());
				getCommand("enderchest").setTabCompleter(new Enderchest());
			}
		} catch (NullPointerException ex) {
			getCommand("enderchest").setExecutor(new Enderchest());
			getCommand("enderchest").setTabCompleter(new Enderchest());
		}
		
		try {
			if (!disabledcommands.contains("cords") &&
			!disabledcommands.contains("all")) {
				getCommand("cords").setExecutor(new Cords());
				getCommand("cords").setTabCompleter(new Cords());
			}
		} catch (NullPointerException ex) {
			getCommand("cords").setExecutor(new Cords());
			getCommand("cords").setTabCompleter(new Cords());
		}
		
		try {
			if (!disabledcommands.contains("tpall") &&
			!disabledcommands.contains("all")) {
				getCommand("tpall").setExecutor(new TpAll());
				getCommand("tpall").setTabCompleter(new TpAll());
			}
		} catch (NullPointerException ex) {
			getCommand("tpall").setExecutor(new TpAll());
			getCommand("tpall").setTabCompleter(new TpAll());
		}
		
		try {
			if (!disabledcommands.contains("s") &&
			!disabledcommands.contains("all")) {
				getCommand("s").setExecutor(new S());
				getCommand("s").setTabCompleter(new S());
			}
		} catch (NullPointerException ex) {
			getCommand("s").setExecutor(new S());
			getCommand("s").setTabCompleter(new S());
		}
		
		// LISTENERS
		if (this.getConfig().getBoolean("enable-pinglistener")) {
			Bukkit.getPluginManager().registerEvents(new PingListener(), this);
		}
		
		if (this.getConfig().getBoolean("enable-antidespawn")) {
			AntiDespawn.customData = new HashMap<>();
			Bukkit.getPluginManager().registerEvents(new AntiDespawn(), this);
		}
		
		System.out.println("BusinessTools have been successfully loaded.");
		if (this.getConfig().getString("config-version").equals(this.getDescription().getVersion()) == false) {
			System.out.println(ChatColor.RED + "BusinessTools config is up to date! Please delete it and reload server.");
		}
	}
	
}
