package fr.maygo.city.scoreboard;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.teams.Team;

public class Scoreboard {

	public static Map<Player, ScoreboardSign> boards = new HashMap<>();
	
	public void sendScoreboard(Player player, Joueur joueur) {
		ScoreboardSign sb = new ScoreboardSign(player, "§6La Cité Féerique");
		sb.create();
	    sb.setLine(0, "§2Émeraudes : §a"+joueur.getCredit());
	    sb.setLine(1, "§2Émeraudes (team) : §a"+Team.getTeam(joueur.getTeam().name()).getCredit());
	    sb.setLine(2, "§3Top : §b"+(Joueur.getJoueurs().indexOf(joueur)+1));
		boards.put(player, sb);
	}

	public void removeScoreboard(Player player) {
		if(Scoreboard.boards.containsKey(player)) {
			boards.get(player).destroy();
		}
	}
	
	public void refresh(Player target) {
		if(boards.containsKey(target)) {
			Joueur joueur = Joueur.getJoueur(target.getUniqueId());
			Team team = Team.getTeam(joueur.getTeam().name());
			boards.get(target).setLine(0, "§2Émeraudes : §a"+joueur.getCredit());
		    boards.get(target).setLine(1, "§2Émeraudes (team) : §a"+team.getCredit());
		    boards.get(target).setLine(2, "§3Top : §b"+(Joueur.joueurss.indexOf(joueur)+1));
		}
	}
}
