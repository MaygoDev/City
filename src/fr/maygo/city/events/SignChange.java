package fr.maygo.city.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChange implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		if(player.isOp()) {
			String[] lines = event.getLines().clone();
			for (int i = 0; i < 3; i++) {
				event.setLine(i, event.getLine(i).replaceAll("&", "§"));
			}
			if (lines[0].equalsIgnoreCase("[CAT]")) {
				event.setLine(0, "§8§m------------");
				if(lines[1].equalsIgnoreCase("Finish")) {
					event.setLine(1, "§6Fin !");
					event.setLine(2, "§eTu y est arrivé");
				}else {
					event.setLine(1, "§6Indice n°"+lines[1]);
					event.setLine(2, "§e"+lines[2]);
				}
				event.setLine(3, "§8§m------------");
			}
		}
	}
	
}
