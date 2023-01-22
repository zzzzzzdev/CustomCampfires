package me.skript.campfires;

import lombok.Getter;
import me.skript.campfires.campfire.CampfireManager;
import me.skript.campfires.command.CampfireCommand;
import me.skript.campfires.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

@Getter
public class Campfires extends JavaPlugin {

    @Getter
    private static Campfires instance;

    private CampfireManager campfireManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        campfireManager = new CampfireManager(this);
        getCommand("campfire").setExecutor(new CampfireCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {


    }
}
