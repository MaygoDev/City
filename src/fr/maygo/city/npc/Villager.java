package fr.maygo.city.npc;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import fr.maygo.city.City;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IMerchant;
import net.minecraft.server.v1_12_R1.ItemStack;
import net.minecraft.server.v1_12_R1.MerchantRecipe;
import net.minecraft.server.v1_12_R1.MerchantRecipeList;
import net.minecraft.server.v1_12_R1.World;

public abstract class Villager {

	Location loc;
	org.bukkit.entity.Villager villager;
	String name;
	MerchantRecipeList trades;

	public Villager(Location loc, String name, MerchantRecipeList trades) {
		this.loc = loc;
		this.name = name;
		this.trades = trades;
	}

	public void spawn() {
		villager = (org.bukkit.entity.Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		villager.setInvulnerable(true);
		villager.setCustomNameVisible(true);
		villager.setCustomName(name);

		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		entityVillager.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		entityVillager.setNoAI(true);
		entityVillager.setInvulnerable(true);
		entityVillager.setSilent(true);
		try {
			Field recipes = entityVillager.getClass().getDeclaredField("trades");
			recipes.setAccessible(true);

			recipes.set(entityVillager, trades);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(trades != null) {
			Bukkit.getPluginManager().registerEvents(new Listener() {
	
				@EventHandler
				public void onInteract(PlayerInteractAtEntityEvent event) {
					if (event.getRightClicked() != null) {
						if (event.getRightClicked() instanceof org.bukkit.entity.Villager) {
							if(villager.getEntityId() == event.getRightClicked().getEntityId()) {
								if(trades == null) {
									event.setCancelled(true);
									return;
								}
								
				        		EntityVillager nmsVillager = ((CraftVillager) villager).getHandle();
				        		EntityHuman human = ((CraftPlayer) event.getPlayer()).getHandle();
				        		
				        		human.openTrade(new IMerchant() {
									
									@Override
									public BlockPosition v_() {return null;}
									
									@Override
									public World u_() {return null;}
									
									@Override
									public void setTradingPlayer(EntityHuman arg0) {}
									
									@Override
									public EntityHuman getTrader() {
										return human;
									}
									
									@Override
									public IChatBaseComponent getScoreboardDisplayName() {return null;}
									
									@Override
									public MerchantRecipeList getOffers(EntityHuman h) {
										return nmsVillager.getOffers(h);
									}
									
									@Override
									public void a(ItemStack arg0) {}
									
									@Override
									public void a(MerchantRecipe arg0) {}
								});
							}
						}
					}
				}
			}, City.INSTANCE);
		}
	}
	
	public void kill() {
		villager.remove();
	}

	public Location getLoc() {
		return loc;
	}

	public org.bukkit.entity.Villager getVillager() {
		return villager;
	}

	public String getName() {
		return name;
	}

	public void setTrades(MerchantRecipeList trades) {
		this.trades = trades;
	}

	public MerchantRecipeList getTrades() {
		return trades;
	}

}
