package fr.maygo.city.options.options;

import org.bukkit.Bukkit;
import org.bukkit.World;

import fr.maygo.city.options.Option;

public class NoPvpOption extends Option {

	public NoPvpOption() {
		super("NoPvp");
	}

	@Override
	public void enable() {
		setState(!isState());
		for(World world : Bukkit.getWorlds()) {
			world.setPVP(isState());
		}
	}

	@Override
	public void disable() {
		setState(!isState());
		for(World world : Bukkit.getWorlds()) {
			world.setPVP(isState());
		}
	}

}
