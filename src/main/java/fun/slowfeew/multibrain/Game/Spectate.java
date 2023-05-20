package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Spectate {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        if (it == null || player.getGameMode().equals(GameMode.CREATIVE))
            return;

        PlayerStatus status = PlayerStatus.getStatus(player.getUniqueId());

        if (status != PlayerStatus.INGAME) {
            e.setCancelled(true);
        }
    }
}
