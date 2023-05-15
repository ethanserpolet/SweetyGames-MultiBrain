package fun.slowfeew.multibrain;

import fun.slowfeew.multibrain.Game.ServerManager;
import fun.slowfeew.multibrain.Game.ServerStatus;
import fun.slowfeew.multibrain.SQL.ModPlayer;
import fun.slowfeew.multibrain.WorldManager.Config;
import fun.slowfeew.multibrain.WorldManager.SetSpawns;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import fun.slowfeew.multibrain.Utils.ItemBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinEvent implements Listener {

    FileConfiguration config = Main.getInstance.getConfig();

    public JoinEvent(Main main) {
    }

    @EventHandler
    public void JoinEvent(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (ModPlayer.getModPlayer(p)) { // REMPLACER PAR GET IF PLAYER IS MOD

            e.setJoinMessage(null);

            if (Main.getInstance.getServerManager().getStatus() == ServerStatus.STARTING || Main.getInstance.getServerManager().getStatus() == ServerStatus.WAITING) {
                SetSpawns.goToSpawn(p);
                return;
            }

            p.teleport(new Location(p.getWorld(), 0, 20, 0));


            return;
        }


        if(Main.getInstance.getServerManager().getStatus() == ServerStatus.WAITING) {

            e.setJoinMessage(config.getString("Messages.Join").replace("&", "§").replace("%player%", p.getName()));
            p.teleport(new Location(p.getWorld(), 0, 20, 0));
            Player player = e.getPlayer();
            SetSpawns.goToSpawn(p);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000000, 250));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000000, 250));
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.updateInventory();
            player.setMaxHealth(4.0D);
            player.setHealth(player.getMaxHealth());

            player.getInventory().setItem(0, (
                    new ItemBuilder(Material.STAINED_CLAY, 1)).setInfinityDurability().setDisplayName("§7Choisir une équipe §8(§7Clic-droit§8)")
                    .flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());

            player.getInventory().setItem(7, (
                    new ItemBuilder(Material.REDSTONE_COMPARATOR, 1)).setInfinityDurability().setDisplayName("§dParamètres §8(§7Clic-droit§8)")
                    .flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());
            player.getInventory().setItem(8, (new ItemBuilder(Material.BED, 1)).setInfinityDurability().setDisplayName("§cQuitter §8(§7Clic-droit§8)")
                    .flag(ItemFlag.HIDE_UNBREAKABLE).flag(ItemFlag.HIDE_ATTRIBUTES).build());

            return;
        }

    }

}
