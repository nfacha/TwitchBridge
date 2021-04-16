package com.nunofacha.twitchbridge;

import com.nunofacha.twitchbridge.libs.FileManager;
import com.nunofacha.twitchbridge.updater.Updater;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
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
        if(Main.pluginConfig.getBoolean("update-check")){
            updateCheck();
        }
        Metrics metrics = new Metrics(this, 10824);
        TwitchChat.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void updateCheck() {
        try {

            if (getConfig().getBoolean("auto-update", true)) {
                Main.logger.info("Using the " + getConfig().getString("update-channel") + " update channel!");
                Updater updater = new Updater("https://raw.githubusercontent.com/nfacha/TwitchBridge/" + getConfig().getString("update-channel") + "/meta.json");
            } else {
                Main.logger.info("Auto updating is disabled!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
