package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maygo.city.npc.MerchantRecipeCrafter;
import fr.maygo.city.npc.Villager;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;

public class ExplorateurVillager extends Villager {

	public ExplorateurVillager() {
		super(new Location(Bukkit.getWorld("world"), 321.5, 83, 267.5, 135.0F, 0.0F), "Explorateur", null);
		MerchantRecipeList list = new MerchantRecipeList();
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.PRISMARINE, 2), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.PRISMARINE, 2, (byte)1), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.PRISMARINE, 2, (byte)2), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.SEA_LANTERN), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		super.setTrades(list);
		super.spawn();
	}

}
