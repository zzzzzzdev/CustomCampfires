package me.skript.campfires.command;

import me.skript.campfires.Campfires;
import me.skript.campfires.campfire.Campfire;
import net.lucaudev.api.chat.Chat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CampfireCommand implements CommandExecutor {

    private Campfires instance;

    public CampfireCommand(Campfires instance){
        this.instance = instance;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            if(args.length == 0){
                for(String s : Arrays.asList("&3&l[&b!&3&l] &bCampfires Plugin", "&b/campfire give (player) (type)", "&b/campfire reload", "&b/campfire list")){
                    sender.sendMessage(Chat.color(s));
                }
                return false;
            }

            if(args[0].equalsIgnoreCase("give")){
                Player player = Bukkit.getPlayer(args[1]);
                Campfire campfire = instance.getCampfireManager().getCampfireFromName(args[2]);
                player.getInventory().addItem(campfire.getItemStack());
            } else if(args[0].equalsIgnoreCase("reload")){
                instance.reloadConfig();
                instance.getCampfireManager().getLocationCampfireMap().clear();
                instance.getCampfireManager().load();
                sender.sendMessage(Chat.color("&bReloaded!"));
            } else if(args[0].equalsIgnoreCase("list")){
                sender.sendMessage(Chat.color("&bAvailable Campfires: &e" + StringUtils.join(instance.getCampfireManager().getCampfireList().stream().map(Campfire::getKeyName).collect(Collectors.toList()), ", ")));
            }
        }
        return true;
    }
}
