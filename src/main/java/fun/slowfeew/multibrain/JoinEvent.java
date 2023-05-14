package fun.slowfeew.multibrain;

import fun.slowfeew.multibrain.WorldManager.SetSpawns;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    FileConfiguration config = Main.getInstance.getConfig();

    public JoinEvent(Main main) {
    }

    @EventHandler
    public void JoinEvent(PlayerJoinEvent e ) {

        Player p = e.getPlayer();

        if(p.hasAI()) { // REMPLACER PAR GET IF PLAYER IS MOD

            e.setJoinMessage(null);


            return;
        }



    }
}
