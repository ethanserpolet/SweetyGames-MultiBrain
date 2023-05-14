package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Game.PlayerStatus;
import fun.slowfeew.multibrain.Game.StatusManager;
import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class ResetBlockAfterRound {

    private Main main;
    public ResetBlockAfterRound(Main main) {
        this.main = main;
    }

    public static List<Location> blockLocation;

    public static List<Location> getBlockLocation() {
        return blockLocation;
    }

    public static void addBlockLocation(Location location) {
        if (!getBlockLocation().contains(location))
            blockLocation.add(location);
    }

    public static void removeBlockLocation() {
        blockLocation.clear();
    }
    public static void ResetMap() {
        for (Location location : getBlockLocation()) {
            location.getBlock().setType(Material.AIR);
        }
        removeBlockLocation();
    }

}
