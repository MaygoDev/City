package fr.maygo.city.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCite implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp()) {
				Location loc = new Location(Bukkit.getWorld("world"), 288.8D, 113.0D, 217.5D, -130.0F, 0.0F);
				player.teleport(loc);
			} else {
				player.sendMessage("§aCoordonées de la cité : ");
				player.sendMessage("§2X : 340");
				player.sendMessage("§2Y : 80");
				player.sendMessage("§2Z : 208");
			}
		}
		return false;
	}

}
