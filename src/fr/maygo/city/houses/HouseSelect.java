package fr.maygo.city.houses;

import java.util.UUID;

import org.bukkit.Location;

public class HouseSelect {
	
	boolean mustClickSign = true;
	UUID clicker;
	Location loc1, loc2;
	
	public HouseSelect(UUID clicker, Location loc1) {
		this.clicker = clicker;
		this.loc1 = loc1;
	}

	public boolean isMustSecondClick() {
		return mustClickSign;
	}

	public void setMustSecondClick(boolean mustSecondClick) {
		this.mustClickSign = mustSecondClick;
	}

	public UUID getClicker() {
		return clicker;
	}

	public void setClicker(UUID clicker) {
		this.clicker = clicker;
	}

	public Location getLoc1() {
		return loc1;
	}

	public void setLoc1(Location loc1) {
		this.loc1 = loc1;
	}

	public Location getLoc2() {
		return loc2;
	}

	public void setLoc2(Location loc2) {
		this.loc2 = loc2;
	}
	
}
