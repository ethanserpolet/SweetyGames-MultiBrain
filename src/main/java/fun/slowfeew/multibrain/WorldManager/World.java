package fun.slowfeew.multibrain.WorldManager;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class World implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerPickupItemEvent e) {
        if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
