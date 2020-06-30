package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maygo.city.npc.MerchantRecipeCrafter;
import fr.maygo.city.npc.Villager;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;

public class MineurVillager extends Villager {

	public MineurVillager() {
		super(new Location(Bukkit.getWorld("world"), 316.5, 83.0, 267.5, 180, 0), "Mineur", null);
		MerchantRecipeList list = new MerchantRecipeList();
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.COAL,16),null,new ItemStack(Material.EMERALD)).setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.IRON_INGOT,8),null,new ItemStack(Material.EMERALD)).setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.GOLD_INGOT,4),null,new ItemStack(Material.EMERALD)).setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.DIAMOND),null,new ItemStack(Material.EMERALD,2)).setInfiteUses().setRewardExp(false).craft());
		super.setTrades(list);
		super.spawn();
	}

}
