package xyz.treppi.lobby.events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHunger implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        World w = e.getEntity().getLocation().getWorld();
        if(w.getName().equalsIgnoreCase("lobby")) {
            ((Player) e.getEntity()).setFoodLevel(20);
        }
    }
}
