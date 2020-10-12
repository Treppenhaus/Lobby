package xyz.treppi.lobby.fun;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class CPSLife implements Listener {
	HashMap<String, Integer> cps = new HashMap<String, Integer>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		
		new Thread(() -> {
			
			int current = cps.get(uuid);
			cps.put(uuid, current + 1);
			
			try {Thread.sleep(1000);} catch (InterruptedException e1) {}
			
			current = cps.get(uuid);
			cps.put(uuid, current - 1);
			
		}).start();
		
		int amount = (50 > cps.get(uuid) && cps.get(uuid) > 0) ? cps.get(uuid) + 1 : 50;
		
//		p.setMaxHealth(amount);
//		p.setHealth(amount);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String uuid = p.getUniqueId().toString();
		
		cps.put(uuid, 0);
	}
}
