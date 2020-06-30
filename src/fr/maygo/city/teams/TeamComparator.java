package fr.maygo.city.teams;

import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		return o2.getCredit() - o1.getCredit();
	}

}
