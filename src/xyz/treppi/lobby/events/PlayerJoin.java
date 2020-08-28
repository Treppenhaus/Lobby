package xyz.treppi.lobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import xyz.treppi.lobby.LobbyLocation;
import xyz.treppi.lobby.LocationManager;
import xyz.treppi.lobby.compass.Compass;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		LobbyLocation spawn = LocationManager.getLocation("spawn");
		
		p.getInventory().clear();
		p.getInventory().setItem(0, Compass.getItem());
		e.setJoinMessage("§b+ §7"+p.getName());
		
		if(spawn != null) p.teleport(spawn.getLocation());
		else p.sendMessage("no location with name \"spawn\" set :c");
	}
}
