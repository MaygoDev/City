package fr.maygo.city.teams;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Team {

	public Teams team;
	public int credit;
	public Set<UUID> joueurs;
	public static List<Team> teams = new ArrayList<>();

	@SuppressWarnings("static-access")
	public Team(Teams team, int credit, Set<UUID> list) {
		this.joueurs = list;
		this.credit = credit;
		this.team = team;
		this.teams.add(this);
	}

	public Teams getTeam() {
		return team;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Set<UUID> getJoueurs() {
		return joueurs;
	}

	public static List<Team> getTeams() {
		return teams;
	}

	public static Team getTeam(String name) {
		for (Team team : teams) {
			if(team.getTeam().name().equalsIgnoreCase(name)) {
				return team;
			}
		}
		return null;
	}

}
