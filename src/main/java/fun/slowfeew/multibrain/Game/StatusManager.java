package fun.slowfeew.multibrain.Game;

import org.bukkit.entity.Player;
import java.util.HashMap;

public class StatusManager {

    private HashMap<Player, PlayerStatus> playerStatus;

    public StatusManager() {
        playerStatus = new HashMap<>();
    }

    public PlayerStatus getStatus(Player player) {
        return playerStatus.get(player);
    }

    public void setStatus(Player player, PlayerStatus status) {
        playerStatus.put(player, status);
    }
}
