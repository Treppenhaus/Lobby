package xyz.treppi.lobby.compass;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.treppi.lobby.soup.Soup;

public class Compass implements Listener {
	
	private static ArrayList<CompassItem> items = new ArrayList<CompassItem>();
	private static Inventory compassInventory = null;
	
	public static ItemStack getItem() {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta meta = compass.getItemMeta();
		
		meta.setDisplayName("§aGame Menu");
		compass.setItemMeta(meta);
		
		return compass;
	}
	
	public static Inventory getInventory() {
		if(compassInventory != null) return compassInventory;
		
		Inventory inv = Bukkit.createInventory(null, 3*9, "§8>> §3§lCompass");
		for(CompassItem item : items) inv.setItem(item.getSlot(), item.getIcon());
		compassInventory = inv;
		
		return inv;
	}
	
	@EventHandler
	public void select(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();
		
		if(clicked == null) return;
		if(!clicked.hasItemMeta()) return;
		
		for(CompassItem item : items) {
			if(item.getIcon().getItemMeta().getDisplayName().equals(clicked.getItemMeta().getDisplayName())) {
				
				p.teleport(item.getLocation().getLocation());
				
			}
		}
	}
	
	@EventHandler
	public void openCompass(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack hand = p.getItemInHand();
		
		if(hand == null) return;
		if(!hand.hasItemMeta()) return;
		
		if(hand.getItemMeta().getDisplayName().equalsIgnoreCase(getItem().getItemMeta().getDisplayName())) {
			p.openInventory(getInventory());
		}
		else if(hand.getItemMeta().getDisplayName().equalsIgnoreCase(Soup.getSoupItem().getItemMeta().getDisplayName())) {
			p.setItemInHand(Soup.getEmptyItem());
		}
	}
	public static void fillItemList() {
		ItemStack spawn = new ItemStack(Material.MAGMA_CREAM);
		ItemMeta spawnMeta = spawn.getItemMeta();
		spawnMeta.setDisplayName("§cSpawn");
		
		spawn.setItemMeta(spawnMeta);
		items.add(new CompassItem(spawn, "spawn", 13));
		
		
		ItemStack sumo = new ItemStack(Material.PRISMARINE_SHARD);
		ItemMeta sumoMeta = sumo.getItemMeta();
		sumoMeta.setDisplayName("§cSumo");
		
		sumo.setItemMeta(sumoMeta);
		items.add(new CompassItem(sumo, "sumo", 10));
		
		
		ItemStack bridging = new ItemStack(Material.BRICK);
		ItemMeta bridgingMeta = bridging.getItemMeta();
		bridgingMeta.setDisplayName("§cBridge");
		
		bridging.setItemMeta(bridgingMeta);
		items.add(new CompassItem(bridging, "bridge", 16));
	}
}
