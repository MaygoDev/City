package fr.maygo.city.display.displays;

import java.util.Collections;

import org.bukkit.Location;

import fr.maygo.city.display.Display;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.joueur.JoueurComparator;

public class JoueursDisplay extends Display {

	public JoueursDisplay(Location loc) {
		super("§bClassement des joueurs", loc.add(0, 1, 0), 10);
		display();
	}

	@Override
	public void display() {
		Collections.sort(Joueur.getJoueurs(), new JoueurComparator());
		for (Joueur joueur : Joueur.joueurss) {
			super.spawnArmorStand((Joueur.joueurss.indexOf(joueur)+1) +" - "+joueur.getTeam().getPrefix()+joueur.getName()+" §b : "+joueur.getCredit()+" §2émeraudes");
		}
	}

}
