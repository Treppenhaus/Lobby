package xyz.treppi.lobby.compass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.treppi.lobby.LobbyPlugin;

public class Compass implements Listener {
	
	private static ArrayList<CompassItem> items = new ArrayList<CompassItem>();
	private static Inventory compassInventory = null;
	
	public static ItemStack getItem() {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta meta = compass.getItemMeta();
		
		meta.setDisplayName("§aCompass");
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
				if(item.getLocation() != null) {
					p.teleport(item.getLocation().getLocation());
				}
				if(item.getCmd() != null && !item.getCmd().equalsIgnoreCase("")) {
					sendPlayerToServer(p, item.getCmd());
				}
			}
		}
	}
	
	@EventHandler
	public void openCompass(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack hand = p.getItemInHand();

		if(e.getAction() == Action.PHYSICAL) return; // dont open on pressure plates
		if(hand == null) return;
		if(!hand.hasItemMeta()) return;
		if(!hand.getItemMeta().hasDisplayName()) return;
		
		if(hand.getItemMeta().getDisplayName().equalsIgnoreCase(getItem().getItemMeta().getDisplayName())) {
			p.openInventory(getInventory());
		}
	}

	public static void fillItemList() {
		ItemStack spawn = new ItemStack(Material.MAGMA_CREAM);
		ItemMeta spawnMeta = spawn.getItemMeta();
		spawnMeta.setDisplayName("§cSpawn");
		spawn.setItemMeta(spawnMeta);

		ItemStack lunarsky = new ItemStack(Material.SEA_LANTERN);
		ItemMeta lunarskyMeta = lunarsky.getItemMeta();
		lunarskyMeta.setDisplayName("§bLunarsky");
		lunarsky.setItemMeta(lunarskyMeta);

		ItemStack build = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta buildMeta = build.getItemMeta();
		buildMeta.setDisplayName("§3Bauserver");
		build.setItemMeta(buildMeta);

		items.add(new CompassItem(spawn, "spawn", null, 11));
		items.add(new CompassItem(build, null, "build", 14));
		items.add(new CompassItem(lunarsky, null, "lunarsky", 15));
	}

	public static void sendPlayerToServer(Player player, String server) {
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			out.writeUTF("Connect");
			out.writeUTF(server);
			player.sendPluginMessage(LobbyPlugin.getPlugin(), "BungeeCord", b.toByteArray());
		}
		catch (Exception e) {
			player.sendMessage("§cError when trying to connect to " + server);
		}
	}
}
