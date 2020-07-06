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

public class CmdTeam implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!sender.isOp())
			return false;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length < 2) {
				player.sendMessage("§cUtilisation : /setteam <joueur> <team>");
			} else {
				Player target = Bukkit.getPlayer(args[0]);
				if (target != null) {
					Teams team;
					try {
						team = Teams.valueOf(args[1].toUpperCase());
						Team.getTeam(Joueur.getJoueur(target.getUniqueId()).getTeam().name()).joueurs.remove(target.getUniqueId());
						Team.getTeam(team.name()).joueurs.add(target.getUniqueId());
						Joueur.getJoueur(target.getUniqueId()).setTeam(team);
						City.INSTANCE.getSb().getPlayerTeam(target).removePlayer(target);
						City.INSTANCE.getSb().getTeam(team.getPower()+team.name()).addPlayer(target);
						player.setDisplayName(Joueur.getJoueur(player.getUniqueId()).getTeam().getPrefix()+player.getName());
					} catch (IllegalArgumentException e) {
						player.sendMessage("§cLa team " + args[1] + " n'existe pas !");
						player.sendMessage("§cListe des teams :");
						for (Teams team1 : Teams.values()) {
							player.sendMessage("§c- " + team1.name());
						}
					}
				} else {
					player.sendMessage("§cLe joueur : §4" + args[0] + "§c est introuvable !");
				}
			}
		}
		return false;
	}

}
