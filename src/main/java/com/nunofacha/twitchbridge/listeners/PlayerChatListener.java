package com.nunofacha.twitchbridge.listeners;

import com.nunofacha.twitchbridge.Main;
import com.nunofacha.twitchbridge.TwitchChat;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().hasPermission("twitch.chat.send") && e.getMessage().startsWith(":")) {
            if(Main.pluginConfig.getBoolean("streamer-only") && !e.getPlayer().getName().equalsIgnoreCase(Main.pluginConfig.getString("streamer-name"))){
                return;
            }
            e.setCancelled(true);
            String msg = e.getMessage().substring(1);
            TwitchChat.sendMessage(msg);
            e.getPlayer().sendMessage(ChatColor.BOLD + (ChatColor.RED + "[TW] " + ChatColor.RESET) + msg);

        }
    }
}
