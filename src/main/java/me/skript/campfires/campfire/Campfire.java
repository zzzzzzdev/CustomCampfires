package me.skript.campfires.campfire;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.skript.campfires.data.Radius;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@AllArgsConstructor @Getter @Setter
public class Campfire {

    private String keyName;

    private int expirationTime;

    private boolean isActive;

    private ItemStack itemStack;

    private Radius radius;

    private List<String> effects;


}
