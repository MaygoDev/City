package fr.maygo.city.npc;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.MerchantRecipe;

public class MerchantRecipeCrafter {
	
	public MerchantRecipe recipe;
	
	public MerchantRecipeCrafter(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2) {
		recipe = new MerchantRecipe(CraftItemStack.asNMSCopy(itemstack), CraftItemStack.asNMSCopy(itemstack1), CraftItemStack.asNMSCopy(itemstack2));
	}
	
	public MerchantRecipeCrafter setInfiteUses() {
		recipe.maxUses = Integer.MAX_VALUE;
		return this;
	}
	
	public MerchantRecipeCrafter setRewardExp(boolean state) {
		recipe.rewardExp = state;
		return this;
	}
	
	public MerchantRecipe craft() {
		return recipe;
	}

}
