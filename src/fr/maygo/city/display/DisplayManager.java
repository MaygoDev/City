package fr.maygo.city.display;

import java.util.ArrayList;
import java.util.List;

import fr.maygo.city.City;

public class DisplayManager {

	public City city;
	private List<Display> displays;

	public DisplayManager(City city) {
		this.city = city;
		this.displays = new ArrayList<>();
	}

	public void registerDisplay(Display display) {
		this.displays.add(display);
	}

	public void updateDisplays() {
		for(Display display : this.displays) {
			display.update();
		}
	}
	
	public void destroysDisplays() {
		for(Display display : this.displays) {
			display.destroy();
		}
		this.displays.clear();
	}

}
