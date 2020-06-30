package fr.maygo.city.houses;

import org.bukkit.Material;

public enum BlocksDenied {
	
	TNT(Material.TNT),
	FIRE(Material.FIRE),
	PISTON_STICKY(Material.PISTON_STICKY_BASE),
	BED(Material.BED_BLOCK),
	PISTON(Material.PISTON_BASE);
	
	Material mat;
	
	BlocksDenied(Material mat){
		this.mat = mat;
	}

	public Material getMat() {
		return mat;
	}
	
}
