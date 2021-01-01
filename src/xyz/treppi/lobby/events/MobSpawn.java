package xyz.treppi.lobby.events;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawn implements Listener {
    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        World w = e.getLocation().getWorld();
        if(w.getName().equalsIgnoreCase("lobby") || e.getEventName().startsWith("arena")) e.setCancelled(true);
    }
}
