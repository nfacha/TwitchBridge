package com.nunofacha.twitchbridge;

import net.engio.mbassy.listener.Handler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent;

public class TwitchChatListener {

    @Handler
    public void onMessageReceived(ChannelMessageEvent e) {
        for (Player p : Main.plugin.getServer().getOnlinePlayers()) {
            if (p.hasPermission("twitch.chat.receive")) {
                p.sendMessage(ChatColor.BOLD + (ChatColor.LIGHT_PURPLE + "[TW] " + ChatColor.RESET) + e.getActor().getNick() + " -> " + e.getMessage());
            }
        }
        Main.logger.info("Received twitch message " + e.getMessage());
    }
}
