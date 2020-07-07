package fr.maygo.city.options.options;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.maygo.city.City;
import fr.maygo.city.options.Option;

public class JumpOption extends Option implements Listener {

	public JumpOption() {
		super("Jump");
		Bukkit.getPluginManager().registerEvents(this, City.getINSTANCE());
	}

	@Override
	public void enable() {
		setState(!isState());
	}

	@Override
	public void disable() {
		setState(!isState());
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if(isState() && event.getMessage().equalsIgnoreCase("jump")) {
			event.setCancelled(true);
			event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 338.5, 113, 209.5, -90, 0));
		}
	}

}
