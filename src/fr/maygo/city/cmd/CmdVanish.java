package fr.maygo.city.cmd;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.maygo.city.City;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CmdVanish implements CommandExecutor {
	
	public static Map<Player, BukkitTask> vanished = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(!sender.isOp()) return false;
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(vanished.containsKey(player)) {
				vanished.get(player).cancel();
				Bukkit.getScheduler().cancelTask(vanished.get(player).getTaskId());
				for(Player players : Bukkit.getOnlinePlayers()) {
					players.showPlayer(player);
				}
				player.sendMessage("§dVanish : OFF");
				vanished.remove(player);
			}else {
				for(Player players : Bukkit.getOnlinePlayers()) {
					players.hidePlayer(player);
				}
				player.sendMessage("§dVanish : ON");
				vanished.put(player, new BukkitRunnable() {
					
					@Override
					public void run() {
						player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§dVous êtes actuellement Vanish."));
					}
				}.runTaskTimer(City.INSTANCE, 0, 60));
			}
		}
		return false;
	}

}
