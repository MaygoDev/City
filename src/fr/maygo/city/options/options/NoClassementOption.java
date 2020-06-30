package fr.maygo.city.options.options;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import fr.maygo.city.City;
import fr.maygo.city.display.displays.JoueursDisplay;
import fr.maygo.city.display.displays.TeamDisplay;
import fr.maygo.city.options.Option;

public class NoClassementOption extends Option {

	public NoClassementOption() {
		super("NoClassement");
	}

	@SuppressWarnings("static-access")
	@Override
	public void enable() {
		setState(!isState());
		City.INSTANCE.getDisplayManager().destroysDisplays();
	}

	@SuppressWarnings("static-access")
	@Override
	public void disable() {
		setState(!isState());
		City.INSTANCE.getDisplayManager().registerDisplay(new JoueursDisplay(new Location(Bukkit.getWorld("world"), 303.5, 112.5, 195.5)));
		City.INSTANCE.getDisplayManager().registerDisplay(new TeamDisplay(new Location(Bukkit.getWorld("world"), 309.5, 112.5, 195.5)));
	}

}
