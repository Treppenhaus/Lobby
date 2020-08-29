package xyz.treppi.lobby.soup;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.treppi.lobby.LobbyPlugin;

public class Soup implements Listener {
	
	@EventHandler
	public void onSoup(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		p.sendMessage("a");
		
		if(p.getItemInHand() == null) return;
		if(!p.getItemInHand().hasItemMeta()) return;
		ItemStack soup = p.getItemInHand();
		
		if(soup.getItemMeta().getDisplayName().equalsIgnoreCase(getSoupItem().getItemMeta().getDisplayName())) {
			p.setItemInHand(getEmptyItem());
		}
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		if(p.getItemInHand() == null) return;
		
		ItemStack item = e.getItemDrop().getItemStack();
		
		if(item.getItemMeta().getDisplayName().equalsIgnoreCase(getEmptyItem().getItemMeta().getDisplayName())) {
			
			e.setCancelled(false);
			
			int slot = LobbyPlugin.getRandomNumberInRange(1, 7);
			p.getInventory().setItem(slot, getSoupItem());
		}
	}
	
	@EventHandler
	public void killSoup(ItemSpawnEvent e) {
		Entity entity = e.getEntity();
		if(entity.getType() == EntityType.DROPPED_ITEM) {
			Item item = (Item) entity;
			
			if(item.getItemStack().getType() == getEmptyItem().getType()) item.remove();
		}
	}
	public static ItemStack getSoupItem() {
		
		ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		ItemMeta soupMeta = soup.getItemMeta();
		
		soupMeta.setDisplayName("§cSoup :o §7(Rightclick)");
		
		soup.setItemMeta(soupMeta);
		return soup;
	}
	
	public static ItemStack getEmptyItem() {
		
		ItemStack empty = new ItemStack(Material.BOWL);
		ItemMeta emptyMeta = empty.getItemMeta();
		
		emptyMeta.setDisplayName("§cdrop me");
		
		empty.setItemMeta(emptyMeta);
		return empty;
	}
}
