package fr.maygo.city.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maygo.city.City;
import fr.maygo.city.options.Option;

public class CmdOption implements CommandExecutor {
	
	public City city;
	
	public CmdOption(City city) {
		this.city = city;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			if(!sender.isOp()) return false;
			Player player = (Player) sender;
			if(args.length == 1) {
				Option option = null;
				for(Option options : city.getOptions()) {
					if(options.getName().equalsIgnoreCase(args[0])) {
						option = options;
						break;
					}
				}
				if(option != null) {
					if(option.isState()) {
						option.disable();
						player.sendMessage("§2L'option "+args[0]+" est désormais §cdésactivé !");
					}else {
						option.enable();
						player.sendMessage("§2L'option "+args[0]+" est désormais §aactivé !");
					}
				}else {
					player.sendMessage("§cL'option "+args[0]+" est introuvable !");
				}
			}else {
				player.sendMessage("§cUtilisation : /option <option>");
			}
		}
		return false;
	}
	
}
