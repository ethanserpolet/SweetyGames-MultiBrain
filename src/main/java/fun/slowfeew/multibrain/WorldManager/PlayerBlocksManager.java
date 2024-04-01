package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class PlayerBlocksManager {

    private Main main;
    public PlayerBlocksManager(Main main) {
        this.main = main;
        placedBlocks = new ArrayList<>();
        brokenBlocks = new ArrayList<>();
    }

    private static List<Location> placedBlocks;
    private static List<Location> brokenBlocks;

    public static void addBrokenBlock(Location location) {
        if (placedBlocks.contains(location)) {
            placedBlocks.remove(location);
            return;
        }
        if (!brokenBlocks.contains(location)) {
            brokenBlocks.add(location);
        }
    }
    public static void addPlacedBlock(Location location) {
        if (brokenBlocks.contains(location)) {
            brokenBlocks.remove(location);
            return;
        }
        if (!placedBlocks.contains(location)) {
            placedBlocks.add(location);
        }
    }

    public static void resetMap() {
        for (Location location : brokenBlocks) {
            Block block = location.getBlock();
            if (block != null) {
                block.setType(Material.SANDSTONE);
            }
        }
        for (Location location : placedBlocks) {
            Block block = location.getBlock();
            if (block != null) {
                block.setType(Material.AIR);
            }
        }
        brokenBlocks.clear();
        placedBlocks.clear();
    }
}