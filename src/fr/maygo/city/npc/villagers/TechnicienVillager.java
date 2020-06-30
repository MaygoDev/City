package fr.maygo.city.npc.villagers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.maygo.city.npc.MerchantRecipeCrafter;
import fr.maygo.city.npc.Villager;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;

public class TechnicienVillager extends Villager {

	public TechnicienVillager() {
		super(new Location(Bukkit.getWorld("world"), 330.5, 83.0, 258.5, 45, 0), "Technicien", null);
		MerchantRecipeList list = new MerchantRecipeList();
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.DISPENSER, 2), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.PISTON_STICKY_BASE), null,
				new ItemStack(Material.EMERALD)).setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.DIODE, 3), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.REDSTONE_TORCH_ON,8), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		list.add(new MerchantRecipeCrafter(new ItemStack(Material.REDSTONE_COMPARATOR,2), null, new ItemStack(Material.EMERALD))
				.setInfiteUses().setRewardExp(false).craft());
		super.setTrades(list);
		super.spawn();
	}

}
