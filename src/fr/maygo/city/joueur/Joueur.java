package fr.maygo.city.joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

import fr.maygo.city.City;
import fr.maygo.city.teams.Team;
import fr.maygo.city.teams.Teams;

public class Joueur {

	UUID id;
	int credit;
	Teams team;
	String name;

	public static Map<UUID, Joueur> joueurs = new HashMap<>();
	public static List<Joueur> joueurss = new ArrayList<>();

	@SuppressWarnings("static-access")
	public Joueur(OfflinePlayer player, int credits, String teamName) {
		this.id = player.getUniqueId();
		this.credit = credits;
		this.team = Teams.valueOf(teamName);
		Team.getTeam(teamName).joueurs.add(id);
		this.name = player.getName();
		this.joueurs.put(id, this);
	}

	public int getCredit() {
		return credit;
	}

	public Teams getTeam() {
		return team;
	}

	public void setTeam(Teams team) {
		this.team = team;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCredit(int credit) {
		this.credit = credit;
		City.getDisplayManager().updateDisplays();
	}

	public void addCredit(int credit) {
		Team.getTeam(team.name()).setCredit(Team.getTeam(team.name()).getCredit() + credit);
		setCredit(getCredit() + credit);
	}

	public void removeCredit(int credit) {
		Team.getTeam(team.name()).setCredit(Team.getTeam(team.name()).getCredit() - credit);
		setCredit(getCredit() - credit);
	}

	public UUID getId() {
		return id;
	}

	public static Joueur getJoueur(UUID id) {
		return joueurs.get(id);
	}

	public static List<Joueur> getJoueurs() {
		joueurss.clear();
		for (Joueur joueur : joueurs.values()) {
			joueurss.add(joueur);
		}
		return joueurss;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

}
