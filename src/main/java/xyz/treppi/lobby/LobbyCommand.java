package xyz.treppi.lobby;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(p.hasPermission(Permission.LOBBY_COMMAND)) {
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("setlocation")) {
						
						String locationname = args[1];
						Location location = p.getLocation();
						
						LobbyLocation l = new LobbyLocation(locationname, location);
						LocationManager.addLocation(l);
						
						p.sendMessage(Messages.SAVED);
					}
				}
				else p.sendMessage(Messages.SYNTAX);
			}
			else p.sendMessage(Messages.NO_PERMISSION);
			
			
		}
		return false;
	}

}
