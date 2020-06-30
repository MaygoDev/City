package fr.maygo.city.display.displays;

import java.util.Collections;

import org.bukkit.Location;

import fr.maygo.city.display.Display;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.joueur.JoueurComparator;
import fr.maygo.city.teams.Teams;

public class PieuvreDisplay extends Display {

	public PieuvreDisplay(Location loc) {
		super("§bClassement des §5Pieuvre", loc, 10);
		display();
	}

	@Override
	public void display() {
		Collections.sort(Joueur.getJoueurs(), new JoueurComparator());
		for (Joueur joueur : Joueur.joueurss) {
			if(joueur.getTeam() == Teams.PIEUVRE) {
				super.spawnArmorStand((Joueur.joueurss.indexOf(joueur)+1) +" - "+joueur.getTeam().getPrefix()+joueur.getName()+" §b : "+joueur.getCredit()+" §2émeraudes");
			}
		}
	}

}
