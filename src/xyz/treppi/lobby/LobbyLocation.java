package xyz.treppi.lobby;

import org.bukkit.Location;

public class LobbyLocation {
	String name;
	Location location;
	
	public LobbyLocation(String name, Location location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
