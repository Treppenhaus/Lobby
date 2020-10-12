package xyz.treppi.lobby;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.treppi.lobby.compass.Compass;
import xyz.treppi.lobby.events.BuildMode;
import xyz.treppi.lobby.events.InventorySort;
import xyz.treppi.lobby.events.ItemDrop;
import xyz.treppi.lobby.events.ItemPickup;
import xyz.treppi.lobby.events.NoDamage;
import xyz.treppi.lobby.events.NoPvP;
import xyz.treppi.lobby.events.NoWeather;
import xyz.treppi.lobby.events.PlayerJoin;
import xyz.treppi.lobby.events.PlayerLeave;
import xyz.treppi.lobby.forbiddencommands.CommandListener;
import xyz.treppi.lobby.fun.CPSLife;
import xyz.treppi.lobby.soup.Soup;

public class LobbyPlugin extends JavaPlugin {
	private static String PATH = "plugins/Lobby/config.yml";
	public void onEnable() {
		
		Compass.fillItemList();
		
		getCommand("l").setExecutor(new LobbyCommand());
		getCommand("build").setExecutor(new BuildMode());
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new BuildMode(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerLeave(), this);
		pm.registerEvents(new ItemDrop(), this);
		pm.registerEvents(new ItemPickup(), this);
		pm.registerEvents(new InventorySort(), this);
		
		pm.registerEvents(new NoPvP(), this);
		pm.registerEvents(new NoDamage(), this);
		pm.registerEvents(new Compass(), this);
		
		pm.registerEvents(new NoWeather(), this);
		
		pm.registerEvents(new CommandListener(), this);
		pm.registerEvents(new Soup(), this);
		pm.registerEvents(new CPSLife(), this);
	}
	public static FileConfiguration getPluginConfig() {
		return YamlConfiguration.loadConfiguration(new File(PATH));
	}
	public static void saveConfig(FileConfiguration config) {
		try {
			config.save(new File(PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
