package me.skript.campfires.campfire;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import me.skript.campfires.Campfires;
import me.skript.campfires.data.Radius;
import net.lucaudev.api.item.ItemBuilder;
import net.lucaudev.api.item.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

@Getter
public class CampfireManager {

    private Campfires instance;

    private List<Campfire> campfireList = Lists.newLinkedList();

    private Map<Location, Campfire> locationCampfireMap = Maps.newHashMap();

    public CampfireManager(Campfires instance) {
        this.instance = instance;
        load();
        new CampfireTask(this).runTaskTimer(instance, 40L, 20L);
    }

    public void load() {
        FileConfiguration config = instance.getConfig();
        for (String key : config.getConfigurationSection("Campfires").getKeys(false)) {
            campfireList.add(new Campfire(key,
                    config.getInt("Campfires." + key + ".Expiration"),
                    false,
                    new ItemBuilder(Material.CAMPFIRE).name(config.getString("Campfires." + key + ".Name")).appendLore(config.getStringList("Campfires." + key + ".Lore")).nbt("campfire", key).build(),
                    new Radius(config.getInt("Campfires." + key + ".Radius.X"), config.getInt("Campfires." + key + ".Radius.X"), config.getInt("Campfires." + key + ".Radius.X")),
                    config.getStringList("Campfires." + key + ".Effects")));
        }
    }

    public Campfire getCampfireFromName(String input) {
        return campfireList.stream().filter(campfire -> campfire.getKeyName().equalsIgnoreCase(input)).findFirst().get();
    }

}
