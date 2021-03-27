package com.nunofacha.twitchbridge;

import com.nunofacha.twitchbridge.libs.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    public static Plugin plugin;
    public static Logger logger;
    public static FileManager fileManager;
    public static YamlConfiguration pluginConfig;
    @Override
    public void onEnable() {
        Main.plugin = this;
        Main.logger = this.getLogger();
        logger.setLevel(Level.FINEST);
        logger.info("Loading config");
        Main.fileManager = new FileManager(this);
        fileManager.getConfig("config.yml").copyDefaults(true).save();
        Main.pluginConfig = fileManager.getConfig("config.yml").get();
        logger.info("Config loaded");
        TwitchChat.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
