package fun.slowfeew.multibrain.Game.Runnables;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Game.StartGame;
import org.bukkit.Bukkit;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LobbyRunnable extends BukkitRunnable {



    public static boolean start = false;
    public static int secondsleft = 10;

    private List<Player> runplayers = new  ArrayList<>();
    private List<UUID> waitplayers = new  ArrayList<>();

    @Override
    public void run() {

        if(LobbyRunnable.start) {
            if(secondsleft == 0) {


                if(!TeamsManager.allTeamsHavePlayer()) {
                    for (Player player: Bukkit.getOnlinePlayers()) {
                        if(PlayerStatus.getStatus(player.getUniqueId()) == PlayerStatus.INSPAWN) {
                            waitplayers.add(player.getUniqueId()); // LIGNE 34
                        }
                    }
                    TeamsManager.fillEmptyTeamsWithPlayers(waitplayers);
                }


                LobbyRunnable.start = false;

                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(TeamsManager.getTeam(p.getUniqueId()) != null) {
                        runplayers.add(p);
                    }


                }
                StartGame.running(runplayers);
            }

            if(secondsleft == 10 || secondsleft == 5 || secondsleft == 4 || secondsleft == 3 || secondsleft == 2 || secondsleft == 1) {
                Bukkit.broadcastMessage("§6[MultiBrain] §fDébut de la partie dans §b" + secondsleft + " §fseconde(s).");
                //send title

                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle("", "§fLa partie commence dans §b" + secondsleft + " §aseconde(s)");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 10, 1);
                }
            }

            secondsleft--;
        }




    }


}
