package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maygo.city.npc.MerchantRecipeCrafter;
import fr.maygo.city.npc.Villager;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;

public class ObjetsUniquesVillager extends Villager {

	public ObjetsUniquesVillager() {
		super(new Location(Bukkit.getWorld("world"), 323.5, 83.0, 266.5, 180, 0), "Objets Uniques", null);
		MerchantRecipeList list = new MerchantRecipeList();
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.SADDLE), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.LEASH), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.NAME_TAG), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.GREEN_RECORD), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.GOLD_RECORD), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_3), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_4), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_5), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_6), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_7), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_8), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_9), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_10), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_11), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.RECORD_12), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.IRON_BARDING), null, new ItemStack(Material.EMERALD, 20))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.GOLD_BARDING), null, new ItemStack(Material.EMERALD, 40))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.DIAMOND_BARDING), null, new ItemStack(Material.EMERALD, 60))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.SKULL_ITEM, 1, (byte) 5), null, new ItemStack(Material.EMERALD, 30))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.SKULL_ITEM, 1, (byte) 1), null, new ItemStack(Material.EMERALD, 10))
				.setInfiteUses().setRewardExp(false).craft());
		super.setTrades(list);
		super.spawn();
	}

}
