package me.skript.campfires.campfire;

import net.lucaudev.api.item.XPotion;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class CampfireTask extends BukkitRunnable {

    private CampfireManager campfireManager;

    public CampfireTask(CampfireManager campfireManager) {
        this.campfireManager = campfireManager;
    }

    @Override
    public void run() {
        if (campfireManager.getLocationCampfireMap().isEmpty()) {
            return;
        }
        campfireManager.getLocationCampfireMap().forEach((location, campfire) -> {
            if(campfire.isActive()){
                for (Entity nearbyEntity : location.getWorld().getNearbyEntities(location, campfire.getRadius().getX(), campfire.getRadius().getY(), campfire.getRadius().getZ())) {

                    if (nearbyEntity == null) {
                        continue;
                    }

                    if (nearbyEntity instanceof Player) {
                        Player player = (Player) nearbyEntity;
                        for (String effect : campfire.getEffects()) {
                            player.addPotionEffect(new PotionEffect(XPotion.matchXPotion(effect.split(":")[0]).get().parsePotionEffectType(),
                                    20 * Integer.parseInt(effect.split(":")[2]), Integer.parseInt(effect.split(":")[1])));
                        }
                    }
                }
            }
        });
    }
}
