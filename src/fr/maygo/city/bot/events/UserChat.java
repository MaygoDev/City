package fr.maygo.city.bot.events;

import java.awt.Color;
import java.util.Collections;

import org.bukkit.Bukkit;

import fr.maygo.city.bot.Bot;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.joueur.JoueurComparator;
import fr.maygo.city.teams.Team;
import fr.maygo.city.teams.TeamComparator;
import fr.maygo.city.teams.Teams;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class UserChat implements EventListener {

	public Bot bot;

	public UserChat(Bot bot) {
		this.bot = bot;
	}

	@Override
	public void onEvent(Event genericEvent) {
		if (genericEvent instanceof MessageReceivedEvent) {
			messageRecievedEvent((MessageReceivedEvent) genericEvent);
		}
	}

	@SuppressWarnings("deprecation")
	private void messageRecievedEvent(MessageReceivedEvent event) {
		if (event.getAuthor().getId().equalsIgnoreCase(event.getJDA().getSelfUser().getId()))
			return;
		if (event.getMessage().getContent().startsWith("/")) {
			String message = event.getMessage().getContent().substring(1);
			String[] args = message.split(" ");

			if (message.startsWith("help")) {
				event.getGuild().getTextChannelById(event.getChannel().getIdLong()).sendMessage(new EmbedBuilder()
						.setTitle(":information_source: Aide")
						.addField("*Voici les commandes du serveur :*",
								"**/help** : *aide du serveur*\n**/classement** : *Affiche le classement des joueurs !*\n",
								false)
						.setColor(Color.GREEN).setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl())
						.build()).queue();
			} else if (message.startsWith("classement")) {
				if (message.equalsIgnoreCase("classement")) {
					Collections.sort(Joueur.getJoueurs(), new JoueurComparator());
					Collections.sort(Team.getTeams(), new TeamComparator());
					String teams = "━━━━━━━━━━━━━━━━━\n";
					String joueurs = "━━━━━━━━━━━━━━━━━\n";
					for (Team team : Team.getTeams()) {
						teams += (Team.getTeams().indexOf(team) + 1) + " - ** "
								+ event.getJDA().getRoleById(team.getTeam().getDiscordRole()).getAsMention() + "** : *"
								+ team.getCredit() + " émeraudes*\n";
					}
					for (Joueur joueur : Joueur.joueurss) {
						joueurs += (Joueur.joueurss.indexOf(joueur) + 1) + " - **"
								+ event.getJDA().getRoleById(joueur.getTeam().getDiscordRole()).getAsMention() + " "
								+ joueur.getName() + "** : *" + joueur.getCredit() + " émeraudes*\n";
					}
					event.getGuild().getTextChannelById(event.getChannel().getIdLong())
							.sendMessage(new EmbedBuilder().setTitle(":ballot_box_with_check: Classement")
									.addField("*Voici le classement de la cité :*", teams, false).setColor(Color.GREEN)
									.setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl()).build())
							.queue();
					event.getGuild().getTextChannelById(event.getChannel().getIdLong())
					.sendMessage(new EmbedBuilder().setTitle(":ballot_box_with_check: Classement")
							.addField("*Voici le classement de la cité :*", joueurs, false).setColor(Color.GREEN)
							.setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl()).build())
					.queue();
				} else if (args.length > 1) {
					if (args[1].equalsIgnoreCase("joueur")) {
						if (Joueur.getJoueur(Bukkit.getOfflinePlayer(args[2]).getUniqueId()) != null) {
							Joueur joueur = Joueur.getJoueur(Bukkit.getOfflinePlayer(args[2]).getUniqueId());
							event.getGuild().getTextChannelById(event.getChannel().getIdLong()).sendMessage(
									new EmbedBuilder().setTitle(":ballot_box_with_check: Classement").addField(
											"*Voici les émeraudes de " + joueur.getName() + " :*",
											(Joueur.joueurss.indexOf(joueur) + 1) + " - **"
													+ event.getJDA().getRoleById(joueur.getTeam().getDiscordRole())
															.getAsMention()
													+ " " + joueur.getName() + "** : *" + joueur.getCredit()
													+ " émeraudes*\n",
											false).setColor(Color.GREEN)
											.setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl())
											.build())
									.queue();
						} else {
							event.getGuild().getTextChannelById(event.getChannel().getIdLong())
									.sendMessage(
											new EmbedBuilder().setTitle(":interrobang: Erreur :")
													.addField("*Une érreur est survenue ! (" + args[2] + " <- ICI)*",
															"**Erreur:** *Le joueur* **" + args[2]
																	+ "** *est introuvable !*",
															false)
													.setColor(Color.RED)
													.setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl())
													.build())
									.queue();
						}
					} else if (args[1].equalsIgnoreCase("team")) {
						Teams teams = null;
						try {
							teams = Teams.valueOf(args[2].toUpperCase());
							Team team = Team.getTeam(teams.name());
							String sub = "━━━━━━━━━━━━━━━━━\n";
							Collections.sort(Joueur.getJoueurs(), new JoueurComparator());
							for (Joueur joueur : Joueur.joueurss) {
								if (joueur.getTeam() == team.getTeam()) {
									sub += (Joueur.joueurss.indexOf(joueur) + 1) + " - **"
											+ event.getJDA().getRoleById(team.getTeam().getDiscordRole()).getAsMention()
											+ " " + joueur.getName() + "** : *" + joueur.getCredit() + " émeraudes*\n";
								}
							}
							event.getGuild().getTextChannelById(event.getChannel().getIdLong())
									.sendMessage(new EmbedBuilder().setTitle(":ballot_box_with_check: Classement")
											.addField("*Voici le classement de la team " + args[2] + " :*", sub, false)
											.setColor(Color.GREEN)
											.setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl())
											.build())
									.queue();
						} catch (IllegalArgumentException e) {
							event.getGuild().getTextChannelById(event.getChannel().getIdLong())
									.sendMessage(
											new EmbedBuilder().setTitle(":interrobang: Erreur :")
													.addField("*Une érreur est survenue ! (" + args[2] + " <- ICI)*",
															"**Erreur:** *La team* **" + args[2]
																	+ "** *est introuvable !*",
															false)
													.setColor(Color.RED)
													.setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl())
													.build())
									.queue();
						}
					} else {
						event.getGuild().getTextChannelById(event.getChannel().getIdLong())
								.sendMessage(new EmbedBuilder().setTitle(":interrobang: Erreur :").addField(
										"*Une érreur est survenue !",
										"**Erreur:** *Utilisation de la commande /classement <joueur/team> <pseudo/nom de la team>*",
										false).setColor(Color.RED)
										.setFooter("Par Maygo", event.getJDA().getSelfUser().getAvatarUrl()).build())
								.queue();
					}
				}
			}
		}
		return;

	}

}
