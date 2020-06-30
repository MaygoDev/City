package fr.maygo.city.cmd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.maygo.city.City;
import fr.maygo.city.bdd.FileUtils;
import fr.maygo.city.houses.House;
import fr.maygo.city.houses.HouseSelect;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.utils.Cuboid;
import fr.maygo.city.utils.ItemBuilder;
import fr.maygo.city.utils.Location;

public class CmdHouse implements CommandExecutor, Listener {

	public City city;
	public List<UUID> selectMode = new ArrayList<>();
	public Map<UUID, HouseSelect> select = new HashMap<>();

	public CmdHouse(City city) {
		this.city = city;
		Bukkit.getPluginManager().registerEvents(this, city);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!sender.isOp())
			return false;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage("§cUtilisation : /house <add/tp/get/list/delete> <id>");
			} else {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("add")) {
						player.getInventory().clear();
						player.getInventory().setItemInMainHand(new ItemBuilder(Material.STICK).setGlowing(true)
								.setName("§dChoisir la position 1").toItemStack());
						selectMode.add(player.getUniqueId());
					} else if (args[0].equalsIgnoreCase("list")) {
						for (House house : city.getHouses()) {
							player.sendMessage("§d§m------------");
							player.sendMessage("§dLa Maison n°" + house.getId() + " :");
							player.sendMessage("§dPrix (volume * 3) : " + house.getPrice());
							player.sendMessage(
									"§dOwner : " + (house.getOwner() == null ? "Personne" : house.getOwner()));
							player.sendMessage("§d§m------------");
						}
					} else if (args[0].equalsIgnoreCase("get")) {
						for (House house : city.getHouses()) {
							if (house.getContent().contains(player)) {
								player.sendMessage("§dVous êtes dans la Maison n°" + house.getId());
								break;
							}
						}
					} else {
						player.sendMessage("§cUtilisation : /house <add/tp/get/list/delete> <id>");
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("tp")) {
						byte id;
						try {
							id = Byte.parseByte(args[1]);
						} catch (Exception e) {
							player.sendMessage("§cVeuillez entrer une id de maison !");
							return false;
						}
						for (House house : city.getHouses()) {
							if (house.getId() == id) {
								player.teleport(house.getContent().getCenter());
								break;
							}
						}
					} else if (args[0].equalsIgnoreCase("get")) {
						byte id;
						try {
							id = Byte.parseByte(args[1]);
						} catch (Exception e) {
							player.sendMessage("§cVeuillez entrer une id de maison !");
							return false;
						}
						for (House house : city.getHouses()) {
							if (house.getId() == id) {
								player.sendMessage("§d§m------------");
								player.sendMessage("§dMaison n°" + house.getId() + " :");
								player.sendMessage("§dPrix (volume * 3) : " + house.getPrice());
								player.sendMessage(
										"§dOwner : " + (house.getOwner() == null ? "Personne" : house.getOwner()));
								player.sendMessage("§d§m------------");
								break;
							}
						}
					} else if (args[0].equalsIgnoreCase("delete")) {
						byte id;
						try {
							id = Byte.parseByte(args[1]);
						} catch (Exception e) {
							player.sendMessage("§cVeuillez entrer une id de maison !");
							return false;
						}
						House toDelete = null;
						for (House house : city.getHouses()) {
							if (house.getId() == id) {
								toDelete = house;
								break;
							}
						}
						if(toDelete != null) {
							File file = new File(city.getDataFolder(), "/houses/HOUSE"+toDelete.getId()+".json");
							try {
								FileUtils.deleteFile(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							city.getHouses().remove(toDelete);
							player.sendMessage("§dVous avez bien supprimé la maison n°"+toDelete.getId());
						}else {
							player.sendMessage("§cLa maison n°"+id+" est introuvable !");
							return false;
						}
					}
				} else {
					player.sendMessage("§cUtilisation : /house <add/tp/get/list/delete> <id>");
				}
			}
		}
		return false;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getClickedBlock() == null)
			return;

		if (selectMode.contains(player.getUniqueId())) {

			if (event.getItem() == null)
				return;
			if (event.getItem().getType() != Material.STICK)
				return;
			if (!select.containsKey(player.getUniqueId())) {
				select.put(player.getUniqueId(),
						new HouseSelect(player.getUniqueId(), event.getClickedBlock().getLocation()));
				player.sendMessage("§dPosition 1 séléctionné !");
				player.getInventory().setItemInMainHand(new ItemBuilder(Material.STICK).setGlowing(true)
						.setName("§dChoisir la position 2").toItemStack());
			} else {
				if (select.get(player.getUniqueId()).isMustSecondClick()) {
					select.get(player.getUniqueId()).setLoc2(event.getClickedBlock().getLocation());
					player.sendMessage("§dPosition 2 séléctionné !");
					player.getInventory().setItemInMainHand(new ItemBuilder(Material.STICK).setGlowing(true)
							.setName("§dChoisir le panneau").toItemStack());
					select.get(player.getUniqueId()).setMustSecondClick(false);
				} else {
					player.sendMessage("§dPanneau séléctionné !");
					Cuboid cuboid = new Cuboid(player.getWorld(),
							select.get(player.getUniqueId()).getLoc1().getBlockX(),
							select.get(player.getUniqueId()).getLoc1().getBlockY(),
							select.get(player.getUniqueId()).getLoc1().getBlockZ(),
							select.get(player.getUniqueId()).getLoc2().getBlockX(),
							select.get(player.getUniqueId()).getLoc2().getBlockY(),
							select.get(player.getUniqueId()).getLoc2().getBlockZ());

					Location sign = new Location(player.getWorld().getName(),
							event.getClickedBlock().getLocation().getBlockX(),
							event.getClickedBlock().getLocation().getBlockY(),
							event.getClickedBlock().getLocation().getBlockZ());

					int number = (city.getHouses().size() + 1);

					House house = new House((byte) number, cuboid.getVolume() * 3, null, sign, cuboid);

					city.getDatabase().saveHouse(house, (byte) number);

					Sign signBlock = (Sign) event.getClickedBlock().getState();
					signBlock.setLine(0, "§d§m------------");
					signBlock.setLine(1, "§dMaison n°" + number);
					signBlock.setLine(2, "§d" + cuboid.getVolume() * 3 + " §2émeraudes");
					signBlock.setLine(3, "§d§m------------");
					signBlock.update();

					player.sendMessage("§d§m------------");
					player.sendMessage("§dLa Maison n°" + number + " a bien été crée avec succès !");
					player.sendMessage("§dPrix (volume * 3) : " + cuboid.getVolume() * 3);
					player.sendMessage("§dOwner : Personne");
					player.sendMessage("§dVolume (en blocs) : " + cuboid.getVolume());
					selectMode.remove(player.getUniqueId());
					select.remove(player.getUniqueId());
				}
			}
		} else {
			if (event.getClickedBlock().getType() == Material.WALL_SIGN) {
				for (House house : city.getHouses()) {
					if (house.getSign().equals(event.getClickedBlock().getLocation())) {
						if(!player.isOp()) {
							if (house.getOwner() == null) {
								if (house.getPrice() <= Joueur.getJoueur(player.getUniqueId()).getCredit()) {
									house.setOwner(player.getUniqueId());
									city.getDatabase().saveHouse(house, house.getId());
									
									Sign signBlock = (Sign) event.getClickedBlock().getState();
									signBlock.setLine(2, player.getDisplayName());
									signBlock.update();
									
									player.sendMessage("§2Cette maison vous appartient désormais !");
									Bukkit.broadcastMessage(player.getDisplayName() + " §2vient d'acheter la maison n°"
											+ house.getId() + " au prix de " + house.getPrice() + "§2 émeraudes !");
								} else {
									player.sendMessage("§cVous avez pas assez d'émeraudes pour acheter cette maison !");
								}
							} else {
								player.sendMessage("§cCette maison appartient à "
										+ Bukkit.getOfflinePlayer(house.getOwner()).getName());
							}
						}else {
							player.sendMessage("§cVous ne pouvez acheter cette maison quand vous êtes OP !");
						}
					}
				}
				return;
			}
		}
	}

}
