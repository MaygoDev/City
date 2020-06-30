package fr.maygo.city.display.displays;

import java.util.Collections;

import org.bukkit.Location;

import fr.maygo.city.display.Display;
import fr.maygo.city.teams.Team;
import fr.maygo.city.teams.TeamComparator;
import fr.maygo.city.teams.Teams;

public class TeamDisplay extends Display {

	public TeamDisplay(Location loc) {
		super("§bClassement des équipes", loc, Teams.values().length);
		display();
	}

	@Override
	public void display() {
		Collections.sort(Team.getTeams(), new TeamComparator());
		for (Team team : Team.getTeams()) {
			super.spawnArmorStand((Team.getTeams().indexOf(team)+1) +" - §3"+team.getTeam().getPrefix()+"§b: "+team.getCredit()+" §2émeraudes");
		}
	}

}
