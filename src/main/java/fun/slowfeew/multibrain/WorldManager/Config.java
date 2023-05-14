package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    static FileConfiguration config = Main.getInstance.getConfig();
    public static Location getLocation(String path) {

        String worldName = config.getString(path + ".world");

        if (worldName == null) {
            worldName = "world";
        }

        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            System.out.println("ERROR: WORLD NOT FOUND");
            return null;
        }

        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float pitch = (float) config.getDouble(path + ".pitch");
        float yaw = (float) config.getDouble(path + ".yaw");

        Location loc = new Location(world, x, y, z, yaw, pitch);
        return loc;
    }

    public static void setLocation(Location loc, String path) {
        config.set(path + ".world", loc.getWorld().getName());
        config.set(path + ".x", loc.getX());
        config.set(path + ".y", loc.getY());
        config.set(path + ".z", loc.getZ());
        config.set(path + ".pitch", loc.getPitch());
        config.set(path + ".yaw", loc.getYaw());
        Main.getInstance.saveConfig();
    }
}
