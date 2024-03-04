package fun.slowfeew.multibrain.Events;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.PlayerManager;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Game.Runnables.LobbyRunnable;
import fun.slowfeew.multibrain.Game.Tablist;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.Utils.ItemBuilder;
import fun.slowfeew.multibrain.WorldManager.SetSpawns;
import fun.slowfeew.proxy.FunAPI;
import fun.slowfeew.spigot.Utils.SpigotPermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class PlayerJoinEvent implements Listener {

    private final Main main;
    private final FileConfiguration config;

    public PlayerJoinEvent(Main main) {
        this.main = main;
        this.config = main.getConfig();
    }


    @EventHandler
    public void onLog(PlayerLoginEvent e) {
        if(FunAPI.getIfPlayerIsMod(e.getPlayer().getName())) {
            Main.IS_MOD.add(e.getPlayer());
        }
        if (ServerStatus.getStatus().equals(ServerStatus.WAITING)) {
            if(PlayerStatus.isFull(PlayerStatus.INSPAWN)) {
                if(!FunAPI.getIfPlayerIsMod(e.getPlayer().getName())) {
                    e.getPlayer().kickPlayer("§cLa partie est en cours de démarrage, veuillez patienter.");
                    return;
                }
            }
        }

    }

    @EventHandler
    public void joinEvent(org.bukkit.event.player.PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Main.PLAYER_PREFIX.put(e.getPlayer(), SpigotPermissionManager.getPrefix(e.getPlayer().getUniqueId().toString()));

        if (Main.IS_MOD.contains(e.getPlayer())) {
            e.setJoinMessage(null);

            joinMod(p);
            return;
        }


        if (ServerStatus.getStatus().equals(ServerStatus.WAITING)) {


            e.setJoinMessage(config.getString("JoinMessage")
                    .replace("&", "§")
                    .replace("%player%", p.getName())
                    .replace("%prefix%", String.valueOf(Objects.requireNonNull(Main.PLAYER_PREFIX.get(e.getPlayer()))))
                    .replace("%online%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()))
                    .replace("%max%", Main.maxPlayer
            ));
            goLobby(p);

            if((PlayerStatus.isFull(PlayerStatus.INSPAWN)) && (!LobbyRunnable.start)) {
                LobbyRunnable.start = true;
                new LobbyRunnable().runTaskTimer(main, 0, 20);
            }
        } else {

            ServerStatus serverStatus = ServerStatus.getStatus();

            switch (serverStatus) {

                case STARTING:
                    p.kickPlayer("§cLa partie est en cours de démarrage, veuillez patienter.");
                    break;

                case IN_GAME:
                    if (!Main.IS_MOD.contains(e.getPlayer()) || TeamsManager.getTeam(p.getUniqueId()) == null) {
                        p.kickPlayer("§cLa partie est en cours, veuillez patienter.");
                        return;
                    }

                    if (TeamsManager.getTeam(p.getUniqueId()) != null && PlayerLeaveEvent.playerLeft.contains(p.getUniqueId())) {

                        p.sendMessage("§d ");
                        p.sendMessage("§6[MultiBrain] §fVous avez été réintégré dans la partie.");
                        p.sendMessage("§e ");

                        PlayerManager.doRespawn(p);
                        Tablist.updateTablist(p);
                    }
                    return;
            }
        }
    }

    private void joinMod(Player p) {

        ServerStatus serverStatus = ServerStatus.getStatus();
        if (serverStatus == ServerStatus.WAITING) {
            SetSpawns.goToSpawn(p);
            return;
        }


        p.teleport(new Location(p.getWorld(), 0, 20, 0));
        return;
        }

    private void goLobby(Player player) {
        SetSpawns.goToSpawn(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000000, 250));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000000, 250));
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.updateInventory();
        PlayerStatus.setStatus(PlayerStatus.INSPAWN, player.getUniqueId());
        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());

        //player.getInventory().setItem(0, (new ItemBuilder(Material.STAINED_CLAY, 1)).setInfinityDurability().setDisplayName("§eChoisir une équipe §8(§7Clic-droit§8)").flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());

        //player.getInventory().setItem(7, (new ItemBuilder(Material.REDSTONE_COMPARATOR, 1)).setInfinityDurability().setDisplayName("§dParamètres §8(§7Clic-droit§8)").flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());
        player.getInventory().setItem(8, (new ItemBuilder(Material.BED, 1)).setInfinityDurability().setDisplayName("§cQuitter §8(§7Clic-droit§8)")
                .flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());


    }
}
