package xyz.treppi.lobby.forbiddencommands;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	private static ArrayList<String> forbiddenCommands = new ArrayList<String>();
	private static String permission = "forbiddencommands.ignore";
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		System.out.println(e.getMessage());
		
		if(e.getPlayer().hasPermission(permission)) return;
		if(forbiddenCommands.size() == 0) fill();
		
		for(String forbidden : forbiddenCommands) {
			if(e.getMessage().startsWith(forbidden)) e.setCancelled(true);
		}
	}
	public static void fill() {
		
		forbiddenCommands.add("pl");
		forbiddenCommands.add("plugin");
		forbiddenCommands.add("plugins");
		forbiddenCommands.add("help");
		forbiddenCommands.add("?");
		forbiddenCommands.add("icanhasbukkit");
		forbiddenCommands.add("lp");
		forbiddenCommands.add("pex");
		forbiddenCommands.add("rl");
		forbiddenCommands.add("stop");
		forbiddenCommands.add("pl");
		forbiddenCommands.add("pl");
		forbiddenCommands.add("pl");
		forbiddenCommands.add("cm");
		
	}
}
