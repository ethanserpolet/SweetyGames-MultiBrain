package fun.slowfeew.multibrain.WorldManager;

import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.commands.CommandDebug;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SandstoneType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Sandstone;

public class PlaceObsidian {

    public static void out(TeamsManager color) {


        FileConfiguration config = Main.getInstance.getConfig();




        CommandDebug.debugSend("Loc1Obs: ", String.valueOf(Config.getLocation("Teams.Base." + color.getTeamName() + ".Obsidian1")));
        CommandDebug.debugSend("Loc2bs: ", String.valueOf(Config.getLocation("Teams.Base." + color.getTeamName() + ".Obsidian2")));


        Location loc1 = Config.getLocation("Teams.Base." + color.getTeamName() + ".Obsidian1");
        Location loc2 = Config.getLocation("Teams.Base." + color.getTeamName() + ".Obsidian2");

        World world = loc1.getWorld();

// Convertir le monde Bukkit en CraftWorld
        CraftWorld craftWorld = (CraftWorld) world;

// Récupérer les coordonnées des deux emplacements
        assert loc2 != null;
        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

// Parcourir toutes les coordonnées dans la zone définie par loc1 et loc2
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Location currentLocation = new Location(world, x, y, z);
                    currentLocation.getBlock().setType(Material.OBSIDIAN);
                    ;

                    // Changer le bloc en obsidienne
                    CommandDebug.debugSend("BlockReplaced: ", String.valueOf(currentLocation));
                }
            }
        }

    }

}
