package fr.maygo.city.events;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.maygo.city.City;
import fr.maygo.city.cmd.CmdVanish;
import fr.maygo.city.joueur.Joueur;

public class PlayerIO implements Listener {

	public City city;

	public PlayerIO(City city) {
		this.city = city;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		city.getDatabase().initPlayer(player);

		city.getScoreboard().getTeam(Joueur.getJoueur(player.getUniqueId()).getTeam().getPower()
				+ Joueur.getJoueur(player.getUniqueId()).getTeam().name()).addPlayer(player);
		player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(0.0D);
		player.setDisplayName(Joueur.getJoueur(player.getUniqueId()).getTeam().getPrefix()+player.getName());
		if(!CmdVanish.vanished.isEmpty()) {
			for(Player vanished : CmdVanish.vanished.keySet()) {
				player.hidePlayer(vanished);
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		final Player player = event.getPlayer();

		city.getDatabase().savePlayer(player);
		if(CmdVanish.vanished.containsKey(player)) {
			CmdVanish.vanished.get(player).cancel();
			Bukkit.getScheduler().cancelTask(CmdVanish.vanished.get(player).getTaskId());
			CmdVanish.vanished.remove(player);
		}
	}

}
