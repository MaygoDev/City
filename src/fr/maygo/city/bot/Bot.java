package fr.maygo.city.bot;

import javax.security.auth.login.LoginException;

import fr.maygo.city.bot.events.UserChat;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Bot {

	private JDA jda;

	public Bot() throws LoginException, IllegalArgumentException, RateLimitedException {
		jda = new JDABuilder(AccountType.BOT).setToken("Njk3NTUxNDIwMjE1NTI1NDE2.XuflJA.HPmO08Qp2vkzhwPxdJzNWD8s9P8")
				.setGame(Game.of("/help | Je te surveille !")).setStatus(OnlineStatus.ONLINE).buildAsync();
		jda.addEventListener(new UserChat(this));
	}

	public void disable() {
		jda.shutdownNow();
	}

}
