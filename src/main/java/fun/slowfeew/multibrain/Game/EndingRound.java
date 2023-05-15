package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.ResetBlockAfterRound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndingRound {

    public void endRound(Teams winner) {


        ResetBlockAfterRound.ResetMap();

        for (Player player : Bukkit.getOnlinePlayers()) {

            String winnercolor = winner.getColor() + winner.getColor();
            Player winnerplayer = winner.getPlayers().get(0);

            if(Main.getInstance.getStatusManager().getStatus(player) == PlayerStatus.INGAME) {

                player.sendTitle("🎉 " + winnercolor + " +1 Point !", "§7Prépare toi pour le prochain round !");
                //ComboFFA.setup(player);
            }
        }
    }
}
