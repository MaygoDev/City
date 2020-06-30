package fr.maygo.city.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.maygo.city.City;
import fr.maygo.city.houses.BlocksDenied;
import fr.maygo.city.houses.House;

public class BlockIO implements Listener {

	public City city;

	public BlockIO(City city) {
		this.city = city;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.getPlayer().isOp())
			return;
		if (city.getCity().contains(event.getBlock())) {
			event.setCancelled(true);
			for (House house : city.getHouses()) {
				if (house.getContent().contains(event.getBlock())) {
					if(house.getOwner() != null) {
						event.setCancelled(
								!house.getOwner().toString().equalsIgnoreCase(event.getPlayer().getUniqueId().toString()));
					}
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getPlayer().isOp())
			return;
		if (city.getCity().contains(event.getBlock())) {
			event.setCancelled(true);
			for (House house : city.getHouses()) {
				if (house.getContent().contains(event.getBlock())) {
					event.setCancelled(
							!house.getOwner().toString().equalsIgnoreCase(event.getPlayer().getUniqueId().toString()));
					for(BlocksDenied denied : BlocksDenied.values()){
						if(event.getBlock().getType() == denied.getMat()) {
							event.setCancelled(true);
							break;
						}
					}
				}
			}
		}
	}

}
