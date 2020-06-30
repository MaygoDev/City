package fr.maygo.city.cmd;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.maygo.city.City;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CmdRoue implements CommandExecutor {

	public City city;

	public CmdRoue(City city) {
		this.city = city;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (sender.isOp()) {
			Player player = (Player) sender;
			new BukkitRunnable() {
				int i = 0;

				@Override
				public void run() {
					i++;
					Player target = (Player) Bukkit.getOnlinePlayers().toArray()[new Random()
							.nextInt(Bukkit.getOnlinePlayers().size())];
					for (Player players : Bukkit.getOnlinePlayers()) {
						players.spigot().sendMessage(ChatMessageType.ACTION_BAR,
								TextComponent.fromLegacyText("§6Roue du destin §e" + target.getName()));
					}
					if (i == 20) {
						Bukkit.broadcastMessage("§6La roue du destin à choisit : §e" + target.getName());
						player.teleport(target.getLocation());
						cancel();
					}
				}
			}.runTaskTimer(city, 0, 3);
		}
		return false;
	}

}
