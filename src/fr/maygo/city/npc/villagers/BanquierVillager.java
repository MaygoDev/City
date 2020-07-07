package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.maygo.city.City;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.npc.VillagerClickable;

public class BanquierVillager extends VillagerClickable {

	public BanquierVillager() {
		super(new Location(Bukkit.getWorld("world"), 305.5, 71.0, 202.5, 90, 0), "§aBanquier", onClick -> {
			int emerald = 0;
			for(ItemStack item : onClick.getPlayer().getInventory().getContents()) {
				if(item != null) {
					if(item.getType() == Material.EMERALD) {
						emerald += item.getAmount();
						if(onClick.getPlayer().getInventory().getItemInOffHand().equals(item)) {
							onClick.getPlayer().getInventory().setItemInOffHand(null);
						}else{
							onClick.getPlayer().getInventory().removeItem(item);
						}
					}else if(item.getType() == Material.EMERALD_BLOCK) {
						emerald += (item.getAmount() * 9);
						if(onClick.getPlayer().getInventory().getItemInOffHand().equals(item)) {
							onClick.getPlayer().getInventory().setItemInOffHand(null);
						}else{
							onClick.getPlayer().getInventory().removeItem(item);
						}
					}
				}
			}
			if(emerald == 0) {
				onClick.getPlayer().sendMessage("§cVous n'avez pas d'émeraudes sur vous !");
			}else {
				onClick.getPlayer().sendMessage("§8§m-----------------------------");
				onClick.getPlayer().sendMessage("§6Maygo §cSecurity");
				onClick.getPlayer().sendMessage(" ");
				task(onClick.getPlayer(), emerald);
			}
		});
		super.spawn();
	}
	
	public static void task(Player player, int emeralds) {
		new BukkitRunnable() {
			int i = 0;
			@Override
			public void run() {
				if(i == 1) {
					player.sendMessage("§9Scan du skin en cours : §b20%");
				}else if(i == 2) {
					player.sendMessage("§9Scan du skin en cours : §b40%");
				}else if(i == 3) {
					player.sendMessage("§9Scan du skin en cours : §b60%");
				}else if(i == 4) {
					player.sendMessage("§9Scan du skin en cours : §b80%");
				}else if(i == 5) {
					player.sendMessage("§9Scan du skin en cours : §b100%");
					player.sendMessage("§bScan du skin fini ! §aAccepté !");
					player.sendMessage("§8§m-----------------------------");
					finished(player, emeralds);
					cancel();
				}
				i++;
			}
		}.runTaskTimer(City.INSTANCE, 0, 10);
	}
	
	public static void finished(Player player, int emerald) {
		Joueur.getJoueur(player.getUniqueId()).addCredit(emerald);
		Joueur.getJoueurs().stream().filter(joueur -> joueur.getTeam() == Joueur.getJoueur(player.getUniqueId()).getTeam()).forEach(joueur -> {
			if(Bukkit.getPlayer(joueur.getId()) != null) {
				City.getINSTANCE().getScoreboard().refresh(Bukkit.getPlayer(joueur.getId()));
			}
		});;
		if(emerald >= 500) {
			Bukkit.broadcastMessage("§a"+player.getName()+" à désposé §2"+emerald+" émeraudes !");
		}
		player.sendMessage("§aVous venez de déposer §2"+emerald+" émeraudes !");
	}

}
