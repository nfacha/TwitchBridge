package com.nunofacha.twitchbridge;

import com.nunofacha.twitchbridge.listeners.PlayerChatListener;
import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.feature.twitch.TwitchSupport;

public class TwitchChat {
    private static Client client;
    private static String channel;
    private static String username;
    private static String token;

    public static void init() {
        Main.logger.info("Initializing chat bridge");
        username = Main.pluginConfig.getString("login-username").toLowerCase();
        token = Main.pluginConfig.getString("login-token");
        channel = Main.pluginConfig.getString("channel-name");
        Main.logger.info("Will connect to channel " + channel + " with username " + username);
        client = Client.builder()
                .nick(username)
                .server()
                .host("irc.chat.twitch.tv")
                .password(token)
                .then().build();
        TwitchSupport.addSupport(client);
        client.connect();
        client.getEventManager().registerEventListener(new TwitchChatListener());
        client.addChannel("#" + channel);
        Main.plugin.getServer().getPluginManager().registerEvents(new PlayerChatListener(), Main.plugin);
        Main.logger.info("Connected to twitch chat!");
    }

    public static void sendMessage(String msg) {
        client.sendMessage("#" + channel, msg);
    }
}
