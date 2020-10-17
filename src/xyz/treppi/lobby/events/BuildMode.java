package xyz.treppi.lobby.events;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import xyz.treppi.lobby.Messages;
import xyz.treppi.lobby.Permission;

public class BuildMode implements Listener, CommandExecutor {
	private static HashMap<String, Boolean> buildMode = new HashMap<String, Boolean>();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(!canBuild(p));
	}
	
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(!canBuild(p));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission(Permission.BUILDMODE)) {
				
				if(canBuild(p)) setBuild(p, false);
				else setBuild(p, true);
				
			}
			else p.sendMessage(Messages.NO_PERMISSION);
		}
		return false;
	}
	
	public static Boolean canBuild(Player p) {
		String uuid = p.getUniqueId().toString();
		
		if(buildMode.get(uuid) == null) return false;
		else if(buildMode.get(uuid) == true) return true;
		else return false;
	}
	
	public static void setBuild(Player p, Boolean build) {
		
		String uuid = p.getUniqueId().toString();
		buildMode.put(uuid, build);
		
		if(build) {
			p.sendMessage("§ayou can now build");
			p.setGameMode(GameMode.CREATIVE);
		}
		else {
			p.setGameMode(GameMode.ADVENTURE);
			p.sendMessage("§cyou cant build any longer :o");
		}
	}
}
