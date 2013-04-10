package fr.univaix.iut.pokebattle.run;

import com.twitter.hbc.httpclient.ControlStreamException;
import fr.univaix.iut.pokebattle.bot.Bot;
import fr.univaix.iut.pokebattle.twitter.Credentials;
import fr.univaix.iut.pokebattle.twitter.TwitterBot;

import java.io.IOException;
import java.io.InputStream;

public class BotRunner {
    static void runBot(Bot bot, String credentialsFileName) {
        try {
            InputStream inputStream = getResourceAsStream(credentialsFileName);
            fr.univaix.iut.pokebattle.tuse.Credentials credentials = Credentials.loadCredentials(inputStream);
            TwitterBot twitterBot = new TwitterBot(bot, credentials);
            twitterBot.startBot();
        } catch (InterruptedException e) {
            System.err.println("Error in BotRunner.java : \n"+e);
        } catch (ControlStreamException e) {
        	System.err.println("Error in BotRunner.java : \n"+e);
        } catch (IOException e) {
        	System.err.println("Error in BotRunner.java : \n"+e);
        }
    }

    static InputStream getResourceAsStream(String fileName) {
        return PokemonMain.class.getClassLoader().getResourceAsStream(fileName);
    }
}