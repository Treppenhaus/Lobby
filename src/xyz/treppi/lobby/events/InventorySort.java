package xyz.treppi.lobby.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventorySort implements Listener {
	
	@EventHandler
	public void onItemSort(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(!BuildMode.canBuild(p));
	}
}
