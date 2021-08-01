package xyz.treppi.lobby;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;


public class LocationManager {
	public static void addLocation(LobbyLocation location) {
		FileConfiguration config = LobbyPlugin.getPluginConfig();
		
		for(int i = 0; true; i++) {
			if(!config.contains("locations."+i)) {
				
				config.set("locations."+i+".name", location.getName());
				config.set("locations."+i+".world", location.getLocation().getWorld().getName());
				
				config.set("locations."+i+".x", location.getLocation().getX());
				config.set("locations."+i+".y", location.getLocation().getY());
				config.set("locations."+i+".z", location.getLocation().getZ());
				
				config.set("locations."+i+".pitch", location.getLocation().getPitch());
				config.set("locations."+i+".yaw", location.getLocation().getYaw());
				
				LobbyPlugin.saveConfig(config);
				return;
			}
		}
	}
	public static ArrayList<LobbyLocation> getLocations() {
		
		FileConfiguration config = LobbyPlugin.getPluginConfig();
		ArrayList<LobbyLocation> locations = new ArrayList<LobbyLocation>();
		
		for(int i = 0; true; i++) {
			if(!config.contains("locations."+i)) return locations;
			
			String world = config.getString("locations."+i+".world");
			String name = config.getString("locations."+i+".name");
			
			double x = config.getDouble("locations."+i+".x");
			double y = config.getDouble("locations."+i+".y");
			double z = config.getDouble("locations."+i+".z");
			
			double pitch = config.getDouble("locations."+i+".pitch");
			double yaw = config.getDouble("locations."+i+".yaw");
			
			Location l = new Location(Bukkit.getWorld(world), x, y, z);
			l.setPitch((float) pitch);
			l.setYaw((float) yaw);
			
			LobbyLocation loc = new LobbyLocation(name, l);
			locations.add(loc);
		}
	}
	public static LobbyLocation getLocation(String name) {
		
		for(LobbyLocation location : getLocations()) {
			if(location.getName().equalsIgnoreCase(name)) return location;
		}
		
		return null;
	}
}
