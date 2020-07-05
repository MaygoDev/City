package fr.maygo.city.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maygo.city.City;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.teams.Team;
import fr.maygo.city.teams.Teams;

public class CmdCredit implements CommandExecutor {

	public City city;

	public CmdCredit(City city) {
		this.city = city;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!sender.isOp())
			return false;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length < 3) {
				player.sendMessage("§cUtilisation : /credit <add/get/remove/set> <pseudo/team> <montant>");
			} else {
				int montant = 0;
				try {
					montant = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					player.sendMessage("§cVeuillez entrer un chiffre valide !");
					return false;
				}

				Joueur joueur = Joueur.getJoueur(Bukkit.getOfflinePlayer(args[1]).getUniqueId());
				Teams team = null;

				try {
					team = Teams.valueOf(args[1].toUpperCase());
				} catch (IllegalArgumentException e) {
					if (joueur == null) {
						player.sendMessage("§cVeuillez entrer une team valide !");
						return false;
					}
				}
				
				if (args[0].equalsIgnoreCase("add")) {
					if (team != null) {
						Team.getTeam(team.name()).setCredit(Team.getTeam(team.name()).getCredit() + montant);
						city.getDisplayManager().updateDisplays();
						player.sendMessage("§aVous avez bien ajouté " + montant + " émeraudes à la team "
								+ Team.getTeam(team.name()).getTeam().getPrefix());
					} else if (joueur != null) {
						joueur.addCredit(montant);
						player.sendMessage("§aVous avez bien ajouté " + montant + " émeraudes à " + joueur.getName());
					}
				} else if (args[0].equalsIgnoreCase("get")) {
					if (team != null) {
						player.sendMessage("§aLa Team "+Team.getTeam(team.name()).getTeam().getPrefix()+"§aest à "+Team.getTeam(team.name()).getCredit()+" émeraudes.");
					} else if (joueur != null) {
						player.sendMessage("§aLe Joueur "+joueur.getName()+"§aest à "+joueur.getCredit()+" émeraudes.");
					}
				} else if (args[0].equalsIgnoreCase("remove")) {
					if (team != null) {
						Team.getTeam(team.name()).setCredit(Team.getTeam(team.name()).getCredit() - montant);
						city.getDisplayManager().updateDisplays();
						player.sendMessage("§aVous avez bien retiré " + montant + " émeraudes à la team "
								+ Team.getTeam(team.name()).getTeam().getPrefix());
					} else if (joueur != null) {
						joueur.removeCredit(montant);
						player.sendMessage("§aVous avez bien retiré " + montant + " émeraudes à " + joueur.getName());
					}
				} else if (args[0].equalsIgnoreCase("set")) {
					if (team != null) {
						Team.getTeam(team.name()).setCredit(montant);
						city.getDisplayManager().updateDisplays();
						player.sendMessage("§aVous avez bien mis les émeraudes de la team "+ Team.getTeam(team.name()).getTeam().getPrefix() +"§aà "+montant+" émeraudes");
					} else if (joueur != null) {
						joueur.setCredit(montant);
						player.sendMessage("§aVous avez bien mis les émeraudes de "+ joueur.getName() +" à "+montant+" émeraudes");
					}
				} else {
					player.sendMessage("§cUtilisation : /credit <add/get/remove/set> <pseudo/team> <montant>");
				}
			}
		}
		return false;
	}

}
