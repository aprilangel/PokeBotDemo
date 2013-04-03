package fr.univaix.iut.pokebattle.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterBuilder {
    private fr.univaix.iut.pokebattle.tuse.Credentials credentials;

    public TwitterBuilder(fr.univaix.iut.pokebattle.tuse.Credentials credentials2) {
        this.credentials = credentials2;
    }

    public Twitter build() {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(credentials.getConsumerKey())
                .setOAuthConsumerSecret(credentials.getConsumerSecret())
                .setOAuthAccessToken(credentials.getToken())
                .setOAuthAccessTokenSecret(credentials.getTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}