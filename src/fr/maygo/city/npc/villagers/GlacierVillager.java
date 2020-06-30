package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maygo.city.npc.MerchantRecipeCrafter;
import fr.maygo.city.npc.Villager;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;

public class GlacierVillager extends Villager {

	public GlacierVillager() {
		super(new Location(Bukkit.getWorld("world"), 322.5, 83, 257.5, 90, 0), "Glacier", null);
		MerchantRecipeList list = new MerchantRecipeList();
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.PACKED_ICE), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.ICE, 4), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.SNOW_BLOCK, 4), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.SNOW_BALL, 16), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		super.setTrades(list);
		super.spawn();
	}

}
