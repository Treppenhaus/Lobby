package xyz.treppi.lobby.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoDamage implements Listener {
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		Entity entity = e.getEntity();
		if(entity instanceof Player) e.setCancelled(true);
	}
}
