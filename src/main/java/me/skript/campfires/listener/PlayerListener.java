package me.skript.campfires.listener;

import me.skript.campfires.Campfires;
import me.skript.campfires.campfire.Campfire;
import net.lucaudev.api.item.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    private Campfires instance;

    public PlayerListener(Campfires instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent blockPlaceEvent) {

        Player player = blockPlaceEvent.getPlayer();

        if(!ItemUtils.isValid(player.getItemInHand())){
            return;
        }

        if(blockPlaceEvent.getBlockPlaced() == null){
            return;
        }

        if(blockPlaceEvent.getBlock() == null){
            return;
        }

        ItemStack itemStack = player.getItemInHand();

        if(!ItemUtils.hasNbt(itemStack, "campfire")){
            return;
        }

        Campfire campfire = instance.getCampfireManager().getCampfireFromName(ItemUtils.getNbtString(itemStack, "campfire"));

        if(campfire == null){
            return;
        }
        campfire.setActive(true);
        instance.getCampfireManager().getLocationCampfireMap().put(blockPlaceEvent.getBlockPlaced().getLocation(), campfire);
        Location location = blockPlaceEvent.getBlockPlaced().getLocation();
        Bukkit.getScheduler().runTaskLater(instance, () -> {
            campfire.setActive(false);
            location.getBlock().setType(Material.AIR);
        }, 20L * campfire.getExpirationTime());
    }


}
