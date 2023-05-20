package fun.slowfeew.multibrain.Game.Manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerManager {





    public static void doRespawn(Player p) {
        Location loc = TeamsManager.getSpawn(Objects.requireNonNull(TeamsManager.getTeam(p.getUniqueId())));

        p.teleport(loc);
        p.getInventory().clear();
        p.setHealth(20);
        ItemsManager.giveItem(p);
        p.getActivePotionEffects().clear();
    }
}
