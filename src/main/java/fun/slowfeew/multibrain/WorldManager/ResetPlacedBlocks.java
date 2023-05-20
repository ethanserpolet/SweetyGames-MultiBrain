package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Main;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ResetPlacedBlocks {

    private Main main;
    public ResetPlacedBlocks(Main main) {
        this.main = main;
    }

    public static List<Location> blockLocation = new ArrayList<>();

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
