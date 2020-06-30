package fr.maygo.city.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdInv implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			if(!sender.isOp()) return false;
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null) {
				player.openInventory(target.getInventory());
			}else {
				player.sendMessage("§cLe joueur "+args[0]+" est introuvable !");
			}
		}
		return false;
	}

}
