package fr.maygo.city.options.options;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import fr.maygo.city.City;
import fr.maygo.city.options.Option;

public class OnlyOrgaOption extends Option implements Listener {

	public OnlyOrgaOption() {
		super("OnlyOrga");
		Bukkit.getPluginManager().registerEvents(this, City.INSTANCE);
	}

	@Override
	public void enable() {
		setState(!isState());
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(!players.isOp()) players.kickPlayer("§6Seulement les Organisateurs peuvent se connecter !");
		}
	}

	@Override
	public void disable() {
		setState(!isState());
	}
	
	@EventHandler
	public void onPlayerJoin(AsyncPlayerPreLoginEvent event) {
		if(isState() && (!Bukkit.getOfflinePlayer(event.getUniqueId()).isOp())) event.disallow(Result.KICK_WHITELIST, "§6Seulement les Organisateurs peuvent se connecter !");
	}

}
