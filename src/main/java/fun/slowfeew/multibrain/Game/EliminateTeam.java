package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Utils.ItemBuilder;
import fun.slowfeew.multibrain.WorldManager.PlaceObsidian;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.List;
import java.util.UUID;

public class EliminateTeam {


    public static void eliminateTeam(TeamsManager team) {

        List<UUID> players = TeamsManager.getPlayers(team);
        for (UUID playerUUID : players) {
            Player player = Bukkit.getPlayer(playerUUID);
            if (player != null) {
                PlayerStatus.setStatus(PlayerStatus.INSPEC, playerUUID);
                player.teleport(new Location(Bukkit.getWorld("world"), 0, 25, 0));
                player.getInventory().clear();
                player.setGameMode(GameMode.SPECTATOR);

                player.getInventory().setItem(8, (new ItemBuilder(Material.BED, 1)).setInfinityDurability().setDisplayName("§cQuitter §8(§7Clic-droit§8)")
                        .flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());

                player.sendMessage("§7 ");
                player.sendMessage("§6[MultiBrain] §f §cVous avez été éliminé !");
                player.sendMessage("§fVous pouvez maintenant regarder les autres joueurs jouer !");
                player.sendMessage("§7 ");
                player.sendMessage("§fVos §bstatistiques §font été §asauvegardées§f, vous pouvez les consulter sur le §bsite§f: §6https://funkraft.gg");
                player.sendMessage("§7 ");
                player.sendTitle("§cVous avez été éliminé !", "§7Vous êtes désormais spectateur !");


                // GIVE POINTS // CREDIT UPDATE SQL ...
            }
        }

        PlaceObsidian.out(team);

        Bukkit.broadcastMessage("§6[MultiBrain] §fL'équipe " + team.getPrefix() + " §fa été éliminée !");

    }
}
