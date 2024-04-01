package fun.slowfeew.multibrain;

import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.ServerManager;
import fun.slowfeew.multibrain.WorldManager.RegisterCommandAndEvents;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main extends JavaPlugin {


    public static List<Player> IS_MOD = new ArrayList<>();
    public static HashMap<Player, String> PLAYER_PREFIX = new HashMap<>();
    public static CharSequence maxPlayer =  "4";
    private static Plugin plugin;


    public static Main getInstance;

    private FileConfiguration config;

    private ServerManager serverManager;

    public ServerManager getServerManager() {
        return serverManager;
    }



    int count = 0;
    int secondsLeft = 10;

    @Override
    public void onEnable() {

        super.onEnable();

        // setup config
        getInstance = this;
        plugin = (Plugin)this;

        saveDefaultConfig();
        config = getConfig();

        serverManager = new ServerManager();



        ServerStatus.setStatus(ServerStatus.WAITING);





        Bukkit.getScheduler().runTask(this, RegisterCommandAndEvents::main);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                try {
                    deleteWorld("MultiBrain");
                    copyWorld("MultiBrainTemplate", "MultiBrain");
                    WorldCreator wc = new WorldCreator("MultiBrain");
                    wc.type(WorldType.FLAT);
                    wc.generatorSettings("2;0;1;"); // Ceci crée un monde plat sans aucun bloc pour certaines versions de Minecraft.
                    Bukkit.getServer().createWorld(wc);
                    getLogger().info("Le monde 'MultiBrain' a été copié et chargé avec succès !");
                } catch (IOException e) {
                    e.printStackTrace();
                    getLogger().severe("Impossible de copier le monde !");
                }
            }
        }, 20L);
    }


    private void copyWorld(String sourceWorldName, String targetWorldName) throws IOException {
        File sourceFolder = new File(Bukkit.getWorldContainer(), sourceWorldName);
        File targetFolder = new File(Bukkit.getWorldContainer(), targetWorldName);

        if (!targetFolder.exists()) {
            copyFolder(sourceFolder.toPath(), targetFolder.toPath());
        }
    }

    private void copyFolder(Path source, Path target) throws IOException {
        Files.walk(source).forEach(sourcePath -> {
            Path relativePath = source.relativize(sourcePath);
            Path targetPath = target.resolve(relativePath);
            try {
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public boolean deleteWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            getLogger().warning("Le monde '" + worldName + "' n'existe pas ou n'est pas chargé !");
            return false;
        }

        // Décharger le monde
        if (Bukkit.unloadWorld(world, true)) {
            // Supprimer les fichiers
            return deleteWorldFolder(((World) world).getWorldFolder().toPath());
        } else {
            getLogger().severe("Impossible de décharger le monde '" + worldName + "'");
            return false;
        }
    }

    private boolean deleteWorldFolder(Path pathToBeDeleted) {
        try {
            Files.walk(pathToBeDeleted)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

            getLogger().info("Le monde a été supprimé avec succès !");
            return true;
        } catch (IOException e) {
            getLogger().severe("Erreur lors de la suppression des fichiers du monde: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}