package fr.maygo.city.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdInfo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!sender.isOp())
			return false;
		if (args.length >= 1) {
			StringBuilder sb = new StringBuilder();
			for (String part : args) {
				sb.append(part + " ");
			}
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("§c§m--------------------§r §8[§6INFORMATION§8] §c§m--------------------");
			Bukkit.broadcastMessage("§d" + sender.getName() + " : §l" + sb.toString());
			Bukkit.broadcastMessage("§c§m--------------------§r §8[§6INFORMATION§8] §c§m--------------------");
			Bukkit.broadcastMessage(" ");
		} else {
			sender.sendMessage("§cUtilisation: /say <messge>");
		}
		return false;
	}

}
