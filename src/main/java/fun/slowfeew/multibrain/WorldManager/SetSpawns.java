package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawns {

    static FileConfiguration config = Main.getInstance.getConfig();


    public static void goToSpawn(Player p) {

        String path = "Spawn";
        String worldName = config.getString(path + ".world");

        if (worldName == null) {
           worldName = "world";
        }

        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            System.out.println("ERROR: WORLD NOT FOUND");
            return;
        }

        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float pitch = (float) config.getDouble(path + ".pitch");
        float yaw = (float) config.getDouble(path + ".yaw");

        Location spawn = new Location(world, x, y, z, yaw, pitch);
        p.teleport(spawn);



    }

}
