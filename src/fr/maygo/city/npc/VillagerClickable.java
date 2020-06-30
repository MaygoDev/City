package fr.maygo.city.npc;

import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.maygo.city.City;
import net.minecraft.server.v1_12_R1.EntityVillager;

public abstract class VillagerClickable extends fr.maygo.city.npc.Villager{

	Villager villager;
	Location loc;
	String name;
	Consumer<PlayerInteractEntityEvent> onClick;

	public VillagerClickable(Location loc, String name, Consumer<PlayerInteractEntityEvent> onClick) {
		super(loc, name, null);
		this.loc = loc;
		this.name = name;
		this.onClick = onClick;
		Bukkit.getPluginManager().registerEvents(new Listener() {

			@EventHandler
			public void onInteract(PlayerInteractEntityEvent event) {
				if (event.getRightClicked() != null) {
					if (event.getRightClicked() instanceof Villager) {
						if(villager.getEntityId() == event.getRightClicked().getEntityId()) {
							event.setCancelled(true);
							onClick.accept(event);
						}
					}
				}
			}
		}, City.INSTANCE);
	}

	public Location getLoc() {
		return loc;
	}

	public String getName() {
		return name;
	}

	public void spawn() {
		villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		villager.setInvulnerable(true);
		villager.setCustomNameVisible(true);
		villager.setCustomName(name);

		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		entityVillager.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		entityVillager.setNoAI(true);
		entityVillager.setInvulnerable(true);
		entityVillager.setSilent(true);
	}

	public void kill() {
		villager.remove();
	}

}
