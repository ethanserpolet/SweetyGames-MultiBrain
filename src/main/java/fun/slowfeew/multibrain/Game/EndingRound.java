package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndingRound {

    public void endRound(Teams winner) {


        for (Player player : Bukkit.getOnlinePlayers()) {


            if(Main.getInstance.getStatusManager().getStatus(player) == PlayerStatus.INGAME) {

                player.sendTitle("\u00A77\u00A6 \u00A7cFin de la manche \u00A77\u00A6", "\u00A77R\u00E9initialisation de la carte en cours..");
                //ComboFFA.setup(player);
            }
        }
    }
}
