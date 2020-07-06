package fr.maygo.city.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

import fr.maygo.city.City;

public class EventCanceled implements Listener {
	
	public boolean isInCity(Location loc) {
		return City.INSTANCE.getCity().contains(loc);
	}
	
	@EventHandler
	public void onBreakItemFrame(HangingBreakByEntityEvent event) {
		if(isInCity(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreakItemFrame(HangingPlaceEvent event) {
		if(isInCity(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreakItemFrame(EntityDamageEvent event) {
		if(isInCity(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerArmorStandManipulateEvent e) {
		if(isInCity(e.getRightClicked().getLocation())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.PHYSICAL) {  
			if(event.getClickedBlock().getType() == Material.SOIL && isInCity(event.getClickedBlock().getLocation())){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerBucket(PlayerBucketEmptyEvent event) {
		if(isInCity(event.getBlockClicked().getLocation())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
		if(isInCity(e.getLocation())) {
			if(e.getEntityType() == EntityType.ARMOR_STAND) return;
			if(e.getEntityType() == EntityType.VILLAGER) return;
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSpawn(VehicleCreateEvent e) {
		if(isInCity(e.getVehicle().getLocation())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSpawn(VehicleEntityCollisionEvent e) {
		if(isInCity(e.getVehicle().getLocation())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		event.setCancelled(event.getMessage().substring(1).startsWith("me") || event.getMessage().substring(1).startsWith("minecraft:me"));
	}
	
}
