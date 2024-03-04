package fun.slowfeew.multibrain.Events;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fun.slowfeew.multibrain.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PluginDisableEvent implements Listener {

    private final Main main;
    private final FileConfiguration config;

    public PluginDisableEvent(Main main) {
        this.main = main;
        this.config = main.getConfig();
    }


    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPluginDisable(org.bukkit.event.server.PluginDisableEvent event) {
        if (event.getPlugin() == main) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
                byteArrayDataOutput.writeUTF("Connect");
                byteArrayDataOutput.writeUTF("lobby_01");
                player.sendPluginMessage(Main.getInstance, "BungeeCord", byteArrayDataOutput.toByteArray());
            }
        }
    }
}
