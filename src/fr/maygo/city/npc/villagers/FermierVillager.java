package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maygo.city.npc.MerchantRecipeCrafter;
import fr.maygo.city.npc.Villager;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;

public class FermierVillager extends Villager {

	public FermierVillager() {
		super(new Location(Bukkit.getWorld("world"), 314.5, 83, 253.5, -90, 0), "Fermier", null);
		MerchantRecipeList list = new MerchantRecipeList();
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.MELON_BLOCK, 2), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.PUMPKIN, 2), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.SUGAR_CANE, 8), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.CAKE), null, new ItemStack(Material.EMERALD, 25))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.COOKIE, 4), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.CARROT_ITEM, 3), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.POTATO_ITEM, 3), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.BREAD, 6), null, new ItemStack(Material.EMERALD, 3))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.GOLDEN_APPLE, 1), null, new ItemStack(Material.EMERALD, 15))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.HAY_BLOCK), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.PUMPKIN_PIE, 2), null, new ItemStack(Material.EMERALD, 5))
				.setInfiteUses().setRewardExp(false).craft());
		super.setTrades(list);
		super.spawn();
	}

}
