
package fr.maygo.city.display;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public abstract class Display {
	
	String title;
	Location loc, initialLoc;
	int limit;
	boolean destroyed;
	int i;
	List<ArmorStand> as;
	ArmorStand titleAs;
	
	public Display(String title, Location loc, int limit) {
		this.title = title;
		this.loc = loc;
		this.initialLoc = loc.clone();
		this.limit = limit;
		this.i = 0;
		this.as = new ArrayList<>();
		this.titleAs = spawnArmorStand(title);
	}
	
	public abstract void display();
	
	public ArmorStand spawnArmorStand(String text) {
		ArmorStand as = null;
		if(this.as.size() < (limit + 1)) {
			as = spawnArmorStand(text,loc.add(0, -0.3, 0));
			this.as.add(as);
		}
		return as;
	}
	
	public ArmorStand spawnArmorStand(String text, Location loc) {
		ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		as.setGravity(false);
		as.setVisible(false);
		as.setBasePlate(false);
		as.setCustomNameVisible(true);
		as.setCustomName(text);
		return as;
	}
	
	public void update() {
		if(destroyed) return;
		clear();
		this.titleAs = spawnArmorStand(title);
		display();
	}
	
	public void destroy() {
		destroyed = true;
		clear();
	}
	
	public void clear() {
		for(ArmorStand as : as) {
			as.remove();
		}
		as.clear();
		loc = initialLoc.clone();
	}

	public String getTitle() {
		return title;
	}

	public Location getLoc() {
		return loc;
	}

	public int getLimit() {
		return limit;
	}

	public List<ArmorStand> getAs() {
		return as;
	}
	
}
