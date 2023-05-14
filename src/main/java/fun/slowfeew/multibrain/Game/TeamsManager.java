package fun.slowfeew.multibrain.Game;

import org.bukkit.entity.Player;
import java.util.HashMap;

public class TeamsManager {

    private HashMap<Player, Teams> playerTeams;

    public TeamsManager() {
        playerTeams = new HashMap<>();
    }

    public Teams getTeams(Player player) {
        return playerTeams.get(player);
    }

    public void setTeams(Player player, Teams Teams) {
        playerTeams.put(player, Teams);
    }
}
