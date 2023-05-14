package fun.slowfeew.multibrain.WorldManager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class RemovePlayer {

    public void outRed() {

        //get location from config
        Location loc1 = new Location(null, 0, 0, 0);
        Location loc2 = new Location(null, 0, 0, 0);

        World world = loc1.getWorld();

        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());

        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    world.getBlockAt(x, y, z).setType(Material.OBSIDIAN);
                }
            }
        }
    }

}
