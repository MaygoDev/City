package fr.maygo.city.teams;

public enum Teams {
	
	ORGANISATEUR("Organisateur", 722186072062165022L, "§6Organisateur ",0),
	LION("Lion", 723934946028159056L, "§cLion ",1),
	REQUIN("Requin", 723935303546699906L, "§9Requin ",2),
	PIEUVRE("Pieuvre", 723935377525571756L, "§5Pieuvre ",3),
	SPECTATEUR("Spectateur", 724230645835890698L, "§7Spectateur ",4);
	
	String name, prefix;
	int power;
	long discordRole;
	
	private Teams(String name, long discordRole, String prefix, int power) {
		this.name = name;
		this.discordRole = discordRole;
		this.prefix = prefix;
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getPower() {
		return power;
	}

	public long getDiscordRole() {
		return discordRole;
	}
	
}
