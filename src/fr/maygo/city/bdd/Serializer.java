package fr.maygo.city.bdd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.maygo.city.City;

public class Serializer {
	
	private Gson gson;
	
	public Serializer(City city) {
		this.gson = createGson();
	}

	private Gson createGson() {
		return new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
	}
	
	public String serialize(Object type) {
		return this.gson.toJson(type);
	}
	
	public Object deserialize(String json, Class<? extends Object> type) {
		return this.gson.fromJson(json, type);
	}
	
}
