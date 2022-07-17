package xyz.treppi.lobby;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.treppi.lobby.compass.Compass;
import xyz.treppi.lobby.eco.RandomCoindrop;
import xyz.treppi.lobby.events.*;
import xyz.treppi.lobby.forbiddencommands.CommandListener;
import xyz.treppi.lobby.scoreboard.ScoreboardController;
public class LobbyPlugin extends JavaPlugin {
	private static String PATH = "plugins/Lobby/config.yml";
	private static LobbyPlugin plugin;


	public  static final boolean enableScoreboard = false, enablesoup = false;

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
		pm.registerEvents(new NoHunger(), this);
		pm.registerEvents(new Compass(), this);
		pm.registerEvents(new NoWeather(), this);
		pm.registerEvents(new CommandListener(), this);
		pm.registerEvents(new MobSpawn(), this);

		if(ecoOn()) pm.registerEvents(new RandomCoindrop(), this);

		plugin = this;

		if(LobbyPlugin.enableScoreboard) ScoreboardController.startScoreboard();
		if(LobbyPlugin.enableScoreboard) if(ecoOn()) RandomCoindrop.startCoindropper();
		this.getServer().getMessenger().registerOutgoingPluginChannel(getPlugin(), "BungeeCord");
	}
	public static LobbyPlugin getPlugin() {
		return plugin;
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

	public static boolean ecoOn() {
		return false;
	}
}
