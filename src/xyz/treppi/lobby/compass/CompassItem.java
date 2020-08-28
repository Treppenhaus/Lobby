package xyz.treppi.lobby.compass;

import org.bukkit.inventory.ItemStack;

import xyz.treppi.lobby.LobbyLocation;
import xyz.treppi.lobby.LocationManager;

public class CompassItem {
	ItemStack icon;
	LobbyLocation location;
	int slot;
	
	public CompassItem(ItemStack icon, String locationname, int slot) {
		this.icon = icon;
		this.slot = slot;
		this.location = LocationManager.getLocation(locationname);
	}

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

	public LobbyLocation getLocation() {
		return location;
	}

	public void setLocation(LobbyLocation location) {
		this.location = location;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	
}
