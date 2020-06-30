package fr.maygo.city.bdd;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.maygo.city.City;
import fr.maygo.city.houses.House;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.teams.Team;
import fr.maygo.city.teams.Teams;
import fr.maygo.city.utils.Cuboid;
import fr.maygo.city.utils.Location;

public class Database {

	public City city;
	private File saveDirJoueurs;
	private File saveDirTeams;
	private File saveDirHouses;

	public Database(City city) {
		this.city = city;
		this.saveDirJoueurs = new File(city.getDataFolder(), "/accounts/");
		this.saveDirTeams = new File(city.getDataFolder(), "/teams/");
		this.saveDirHouses = new File(city.getDataFolder(), "/houses/");
	}

	@SuppressWarnings("static-access")
	public void initPlayer(Player player) {
		boolean mustUpdate = false;	
		final File file = new File(saveDirJoueurs, player.getUniqueId().toString() + ".json");
		int credits = 0;
		Teams team = Teams.SPECTATEUR;

		if (file.exists()) {
			final Serializer serializer = city.getSerializer();
			try {
				final String json = FileUtils.loadFile(file);
				Joueur joueur = (Joueur) serializer.deserialize(json, Joueur.class);
				credits = joueur.getCredit();
				team = joueur.getTeam();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			credits = 0;
			team = Teams.SPECTATEUR;
			mustUpdate = true;
		}
		new Joueur(player, credits, team.name());
		if (mustUpdate) {
			city.getDisplayManager().updateDisplays();
		}
	}

	public void savePlayer(Player player) {
		final File file = new File(saveDirJoueurs, player.getUniqueId().toString() + ".json");
		final Serializer serializer = city.getSerializer();
		final Joueur joueur = Joueur.getJoueur(player.getUniqueId());
		final String json = serializer.serialize(joueur);

		try {
			FileUtils.saveFile(file, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		saveTeam(Team.getTeam(Joueur.getJoueur(player.getUniqueId()).getTeam().name()));
	}
	
	public void initTeam(Teams team) {
		final File file = new File(saveDirTeams, team.name() + ".json");
		int credits = 0;
		Set<UUID> joueurs = new HashSet<>();

		if (file.exists()) {
			final Serializer serializer = city.getSerializer();
			try {
				final String json = FileUtils.loadFile(file);
				Team teams = (Team) serializer.deserialize(json, Team.class);
				credits = teams.getCredit();
				joueurs = teams.getJoueurs();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			credits = 0;
		}
		new Team(team, credits, joueurs);
	}

	public void saveTeam(Team team) {
		final File file = new File(saveDirTeams, team.getTeam().name() + ".json");
		final Serializer serializer = city.getSerializer();
		final String json = serializer.serialize(team);

		try {
			FileUtils.saveFile(file, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initTeam(byte id) {
		final File file = new File(saveDirHouses, "HOUSE"+id + ".json");
		UUID owner = null;
		byte iD = 0;
		int price = 0;
		Location sign = null;
		Cuboid content = null;

		if (file.exists()) {
			final Serializer serializer = city.getSerializer();
			try {
				final String json = FileUtils.loadFile(file);
				House house = (House) serializer.deserialize(json, House.class);
				price = house.getPrice();
				owner = house.getOwner();
				sign = house.getSign();
				content = house.getContent();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		new House(iD, price, owner, sign, content);
	}

	public void saveHouse(House house, int i) {
		final File file = new File(saveDirHouses, "HOUSE"+ i + ".json");
		final Serializer serializer = city.getSerializer();
		final String json = serializer.serialize(house);

		try {
			FileUtils.saveFile(file, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
