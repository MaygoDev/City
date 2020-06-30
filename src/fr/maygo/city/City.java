package fr.maygo.city;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.OptionStatus;

import fr.maygo.city.bdd.Database;
import fr.maygo.city.bdd.FileUtils;
import fr.maygo.city.bdd.Serializer;
import fr.maygo.city.bot.Bot;
import fr.maygo.city.cmd.CmdCite;
import fr.maygo.city.cmd.CmdEc;
import fr.maygo.city.cmd.CmdHouse;
import fr.maygo.city.cmd.CmdInfo;
import fr.maygo.city.cmd.CmdInv;
import fr.maygo.city.cmd.CmdOption;
import fr.maygo.city.cmd.CmdRoue;
import fr.maygo.city.cmd.CmdTeam;
import fr.maygo.city.cmd.CmdVanish;
import fr.maygo.city.display.DisplayManager;
import fr.maygo.city.display.displays.JoueursDisplay;
import fr.maygo.city.display.displays.LionDisplay;
import fr.maygo.city.display.displays.PieuvreDisplay;
import fr.maygo.city.display.displays.RequinDisplay;
import fr.maygo.city.display.displays.TeamDisplay;
import fr.maygo.city.events.BlockIO;
import fr.maygo.city.events.EventCanceled;
import fr.maygo.city.events.PlayerChat;
import fr.maygo.city.events.PlayerIO;
import fr.maygo.city.events.PlayerInteract;
import fr.maygo.city.events.PlayerMove;
import fr.maygo.city.events.SignChange;
import fr.maygo.city.houses.House;
import fr.maygo.city.joueur.Joueur;
import fr.maygo.city.npc.Villager;
import fr.maygo.city.npc.villagers.BanquierVillager;
import fr.maygo.city.npc.villagers.ExplorateurVillager;
import fr.maygo.city.npc.villagers.FermierVillager;
import fr.maygo.city.npc.villagers.GlacierVillager;
import fr.maygo.city.npc.villagers.MineurVillager;
import fr.maygo.city.npc.villagers.ObjetsUniquesVillager;
import fr.maygo.city.npc.villagers.TechnicienVillager;
import fr.maygo.city.options.Option;
import fr.maygo.city.options.options.JumpOption;
import fr.maygo.city.options.options.NoClassementOption;
import fr.maygo.city.options.options.NoPvpOption;
import fr.maygo.city.options.options.OnlyOrgaOption;
import fr.maygo.city.teams.Teams;
import fr.maygo.city.utils.Cuboid;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class City extends JavaPlugin {

	public static City INSTANCE;

	private Bot bot;
	private Scoreboard sb;
	private Serializer serializer;
	private Database db;
	private Cuboid city;
	private List<Villager> villagers = new ArrayList<>();
	private List<House> houses = new ArrayList<>();
	private List<Option> options = new ArrayList<>();
	private static DisplayManager displayManager;

	@SuppressWarnings("static-access")
	@Override
	public void onEnable() {
		INSTANCE = this;
		this.serializer = new Serializer(this);
		this.displayManager = new DisplayManager(this);
		this.db = new Database(this);
		this.sb = Bukkit.getScoreboardManager().getMainScoreboard();
		registerTeams();
		registerPlayers();
		registerUtils();
		registerHouses();
		registerOptions();
		registerCommands();
		registerVillagers();
		registerDisplays(displayManager);
		registerEvents(getServer().getPluginManager());
		initBot();
	}

	@Override
	public void onDisable() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			db.savePlayer(players);
		}
		bot.disable();
		displayManager.destroysDisplays();
		killVillagers();
	}

	private void registerDisplays(DisplayManager displayManager) {
		displayManager.registerDisplay(new JoueursDisplay(new Location(Bukkit.getWorld("world"), 303.5, 112.5, 195.5)));
		displayManager.registerDisplay(new LionDisplay(new Location(Bukkit.getWorld("world"), 302.0, 113.0, 224.5)));
		displayManager.registerDisplay(new RequinDisplay(new Location(Bukkit.getWorld("world"), 299.0, 113.0, 228.0)));
		displayManager.registerDisplay(new PieuvreDisplay(new Location(Bukkit.getWorld("world"), 296.0, 113.0, 224.5)));
		displayManager.registerDisplay(new TeamDisplay(new Location(Bukkit.getWorld("world"), 309.5, 112.5, 195.5)));
	}

	private void registerCommands() {
		getCommand("cite").setExecutor(new CmdCite());
		getCommand("option").setExecutor(new CmdOption(this));
		getCommand("house").setExecutor(new CmdHouse(this));
		getCommand("setteam").setExecutor(new CmdTeam());
		getCommand("ec").setExecutor(new CmdEc());
		getCommand("info").setExecutor(new CmdInfo()); 
		getCommand("inv").setExecutor(new CmdInv());
		getCommand("vanish").setExecutor(new CmdVanish());
		getCommand("roue").setExecutor(new CmdRoue(this));
	}

	private void registerPlayers() {
		File dir = new File(getDataFolder(), "/accounts/");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				final Serializer serializer = getSerializer();
				try {
					final String json = FileUtils.loadFile(child);
					Joueur joueur = (Joueur) serializer.deserialize(json, Joueur.class);
					new Joueur(Bukkit.getOfflinePlayer(joueur.getId()), joueur.getCredit(), joueur.getTeam().name());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void registerTeams() {
		for (Teams team : Teams.values()) {
			if (sb.getTeam(team.getPower() + team.name()) == null) {
				Team teams = sb.registerNewTeam(team.getPower() + team.name());
				teams.setPrefix(team.getPrefix());
				teams.setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, OptionStatus.NEVER);
			}
			if (!new File(getDataFolder(), "/teams/" + team.name() + ".json").exists()) {
				db.saveTeam(new fr.maygo.city.teams.Team(team, 0, new HashSet<>()));
			} else {
				db.initTeam(team);
			}
		}
	}

	private void registerHouses() {
		File dir = new File(getDataFolder(), "/houses/");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				final Serializer serializer = getSerializer();
				try {
					final String json = FileUtils.loadFile(child);
					House house = (House) serializer.deserialize(json, House.class);
					new House(house.getId(), house.getPrice(), house.getOwner(), house.getSign(), house.getContent());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for (House house : houses) {
			Sign signBlock = (Sign) house.getSign().toLocation().getBlock().getState();

			signBlock.setLine(0, "§d§m------------");
			signBlock.setLine(1, "§dMaison n°" + house.getId());
			signBlock.setLine(2, house.getOwner() == null ? "§d" + house.getContent().getVolume() * 3 + " §2émeraudes" : Joueur.getJoueur(house.getOwner()).getTeam().getPrefix()+Bukkit.getOfflinePlayer(house.getOwner()).getName());
			signBlock.setLine(3, "§d§m------------");
			
			signBlock.update();
		}
	}
	
	private void registerOptions() {
		options.add(new NoClassementOption());
		options.add(new NoPvpOption());
		options.add(new OnlyOrgaOption());
		options.add(new JumpOption());
	}

	private void initBot() {
		try {
			bot = new Bot();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		}
	}

	private void registerVillagers() {
		villagers.add(new MineurVillager());
		villagers.add(new ExplorateurVillager());
		villagers.add(new FermierVillager());
		villagers.add(new GlacierVillager());
		villagers.add(new ObjetsUniquesVillager());
		villagers.add(new TechnicienVillager());

		villagers.add(new BanquierVillager());
	}

	private void killVillagers() {
		for (Villager villager : villagers) {
			villager.kill();
		}
	}

	private void registerUtils() {
		city = new Cuboid(Bukkit.getWorld("world"), 68, 0, 580, 682, 255, -50);
		for (Entity entity : Bukkit.getWorld("world").getEntities()) {
			if (!(entity instanceof Player)) {
				if (city.contains(entity.getLocation())) {
					entity.remove();
				}
			}
		}
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.broadcastMessage("§d§m------------------------------------");
				Bukkit.broadcastMessage("§dSauvegarde des joueurs ...");
				for (Player players : Bukkit.getOnlinePlayers()) {
					getDatabase().savePlayer(players);
				}
				Bukkit.savePlayers();
				Bukkit.broadcastMessage("§dSauvegarde des mondes ...");
				for (World world : Bukkit.getWorlds()) {
					world.save();
				}
				Bukkit.broadcastMessage("§d§m------------------------------------");

			}
		}.runTaskTimer(this, 0, 20 * 60 * 60);
	}

	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new PlayerIO(this), this);
		pm.registerEvents(new BlockIO(this), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new SignChange(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerInteract(this), this);
		pm.registerEvents(new EventCanceled(), this);
	}

	public static DisplayManager getDisplayManager() {
		return displayManager;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public Database getDatabase() {
		return db;
	}

	public static City getINSTANCE() {
		return INSTANCE;
	}

	public List<Villager> getVillagers() {
		return villagers;
	}

	public Scoreboard getScoreboard() {
		return sb;
	}

	public Cuboid getCity() {
		return city;
	}

	public Bot getBot() {
		return bot;
	}

	public void setBot(Bot bot) {
		this.bot = bot;
	}

	public Scoreboard getSb() {
		return sb;
	}

	public void setSb(Scoreboard sb) {
		this.sb = sb;
	}

	public List<House> getHouses() {
		return houses;
	}

	public List<Option> getOptions() {
		return options;
	}

}
