package fr.maygo.city.houses;

import java.util.UUID;

import fr.maygo.city.City;
import fr.maygo.city.utils.Cuboid;
import fr.maygo.city.utils.Location;

public class House {

	UUID owner;
	int id;
	int price;
	Location sign;
	Cuboid content;

	public House(int id, int price, UUID owner, Location sign, Cuboid content) {
		this.id = id;
		this.sign = sign;
		this.price = price;
		this.owner = owner;
		this.content = content;
		City.INSTANCE.getHouses().add(this);
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public Cuboid getContent() {
		return content;
	}

	public Location getSign() {
		return sign;
	}

}
