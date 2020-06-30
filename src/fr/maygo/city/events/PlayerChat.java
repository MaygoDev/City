package fr.maygo.city.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		if(event.getPlayer().isOp()) message.replaceAll("&", "§");
		event.setFormat(event.getPlayer().getDisplayName()+" §r: "+message);
	}
	
}
