package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;

public class PlayerLeaveEvent implements Listener {

    public static List<UUID> playerLeft = new ArrayList<>();
    private final Main main;
    private final FileConfiguration config;

    public PlayerLeaveEvent(Main main) {
        this.main = main;
        this.config = main.getConfig();
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent e) {
        Main plugin = Main.getInstance;

        if (ServerStatus.getStatus() == ServerStatus.WAITING) {
            Player p = e.getPlayer();

            e.setQuitMessage(config.getString("LeaveMessage")
                    .replace("&", "§")
                    .replace("%player%", p.getName())
                    .replace("%prefix%", String.valueOf(Objects.requireNonNull(Main.PLAYER_PREFIX.get(e.getPlayer()))))
                    .replace("%online%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size() - 1))
                    .replace("%max%", Main.maxPlayer)
            );


                TeamsManager team = TeamsManager.getTeam(p.getUniqueId());
                if (team != null) {
                    team.removePlayer(p.getUniqueId());
                }

        } else if(ServerStatus.getStatus() == ServerStatus.IN_GAME) {
                e.setQuitMessage(null);
                Player p = e.getPlayer();

                if(TeamsManager.getTeam(p.getUniqueId()) != null) {

                    Bukkit.broadcastMessage("§c ");
                    Bukkit.broadcastMessage("§6[MultiBrain] §fLe joueur " + TeamsManager.getTeam(p.getUniqueId()).getPrefix() + " " + p.getName() + " §fc quitté la partie.");
                    Bukkit.broadcastMessage("§8(§cIl a 35 secondes pour rejoindre à nouveau la partie. Au delà de ce délai, il sera éliminé.§8)");
                    Bukkit.broadcastMessage("§c ");
                    playerLeft.add(p.getUniqueId());

                    Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getInstance, () -> {

                        if(!Bukkit.getPlayer(p.getUniqueId()).isOnline() || Bukkit.getPlayer(p.getUniqueId()) == null) {
                            Bukkit.broadcastMessage("§c ");
                            Bukkit.broadcastMessage("§6[MultiBrain] §fLe joueur " + TeamsManager.getTeam(p.getUniqueId()).getPrefix() + " " + p.getName() + " §fa été éliminé.");
                            Bukkit.broadcastMessage("§c ");
                            playerLeft.remove(p.getUniqueId());
                        }

                    }, 20 * 35L);

                }
        }
    }

}
