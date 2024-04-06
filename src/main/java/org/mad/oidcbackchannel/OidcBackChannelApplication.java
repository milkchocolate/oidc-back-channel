package org.mad.oidcbackchannel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@SpringBootApplication
public class OidcBackChannelApplication implements CommandLineRunner {
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public OidcBackChannelApplication(
            OAuth2AuthorizedClientManager authorizedClientManager
    ) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(OidcBackChannelApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var authorizedClient = getOAuth2AuthorizedClient();
        System.out.println("=== Access Token ===");
        System.out.println(authorizedClient.getAccessToken().getTokenValue());
    }

    private OAuth2AuthorizedClient getOAuth2AuthorizedClient() {
        var authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("demo-client")
                .principal("anything")
                .build();
        return this.authorizedClientManager.authorize(authorizeRequest);
    }
}
