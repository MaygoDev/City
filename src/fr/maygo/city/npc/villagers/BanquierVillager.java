package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
				Joueur.getJoueur(onClick.getPlayer().getUniqueId()).addCredit(emerald);
				if(emerald >= 500) {
					Bukkit.broadcastMessage("§a"+onClick.getPlayer().getName()+" à désposé §2"+emerald+" émeraudes !");
				}
				onClick.getPlayer().sendMessage("§aVous venez de déposer §2"+emerald+" émeraudes !");
			}
		});
		super.spawn();
	}

}
