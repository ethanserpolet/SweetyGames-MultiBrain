package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerManager {

    public static List<Player> CantMove;

    public static void respawnAll() {

        for(Player p : Bukkit.getOnlinePlayers()) {

            if(Main.getInstance.getStatusManager().getStatus(p) == PlayerStatus.INGAME) {


                doRespawn(p);

            }
        }

    }

    private static void doRespawn(Player p) {
        p.getInventory().clear();
        p.setHealth(20);
        ItemsManager.giveItems(p);
    }

    public static void respawn(Player p) {

            if(Main.getInstance.getStatusManager().getStatus(p) == PlayerStatus.INGAME) {

                doRespawn(p);

            }

    }
}
