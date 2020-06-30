package fr.maygo.city.utils;

import org.bukkit.Bukkit;

public class Location {
	
	String worldName;
	int x;
	int y;
	int z;
	
	public Location(String world, int x, int y, int z) {
		this.worldName = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public org.bukkit.Location toLocation(){
		return new org.bukkit.Location(Bukkit.getWorld(worldName), x, y, z);
	}
	
	public boolean equals(org.bukkit.Location loc) {
		return getX() == loc.getBlockX() && getY() == loc.getBlockY() && getZ() == loc.getBlockZ();
	}
	
}
