package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.commands.CommandDebug;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SandstoneType;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sandstone;

import java.util.ArrayList;
import java.util.List;

public class ResetBrokenBlocks {

    private Main main;
    public ResetBrokenBlocks(Main main) {
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
            Block block = location.getBlock();
            if (block != null) {
                block.setType(Material.SANDSTONE);
            }
        }
        removeBlockLocation();
    }
}
