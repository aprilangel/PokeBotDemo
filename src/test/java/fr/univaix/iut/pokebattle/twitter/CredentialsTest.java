package fr.univaix.iut.pokebattle.twitter;

import org.junit.Test;

import java.io.InputStream;

import static org.fest.assertions.Assertions.assertThat;

public class CredentialsTest {
    @Test
    public void testLoadCredentialsGivenAValidPropertiesFileShouldReturnAValidCredentials() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./twitter4j.properties");
        Credentials credentials = Credentials.loadCredentials(inputStream);
        assertThat(credentials.getConsumerKey()).isEqualTo("zLON1qhQxVFysMBMbui4UQ");
        assertThat(credentials.getConsumerSecret()).isEqualTo("jNfNeENZqb64oJOk1EZwv3opBnSCuPrjsJ4XHt9pE");
        assertThat(credentials.getToken()).isEqualTo("1264604917-Ryc4uSYFAqAuO4JbC0hCYtD8G2CTXCDhYQWREHC");
        assertThat(credentials.getTokenSecret()).isEqualTo("e5mLYBYp2niMCHHx9OJ8pd3PDL4xT69Wg2JCO7qCsjA");
    }
}
