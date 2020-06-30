package fr.maygo.city.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.maygo.city.City;
import fr.maygo.city.houses.House;

public class PlayerInteract implements Listener {

	public City city;

	public PlayerInteract(City city) {
		this.city = city;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getClickedBlock() == null)
			return;
		if (event.getPlayer().isOp())
			return;
		if (city.getCity().contains(event.getClickedBlock())) {
			for (House house : city.getHouses()) {
				if (house.getContent().contains(event.getClickedBlock())) {
					if(house.getOwner() != null) {
						event.setCancelled(
								!house.getOwner().toString().equalsIgnoreCase(event.getPlayer().getUniqueId().toString()));
					}
				}
			}
		}
	}

}
