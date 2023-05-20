package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Events.PlayerMoveEvent;
import fun.slowfeew.multibrain.Game.Enum.PlayerStatus;
import fun.slowfeew.multibrain.Game.Enum.ServerStatus;
import fun.slowfeew.multibrain.Game.Manager.ItemsManager;
import fun.slowfeew.multibrain.Game.Manager.TeamsManager;
import fun.slowfeew.multibrain.Main;
import fun.slowfeew.multibrain.WorldManager.Config;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class StartRound {

    public static int round = 0;

    public static HashMap<Player, Integer> Duration = new HashMap<>();



    // Import the Vector class

    // Inside your event method...

    public static void start(Player p) {

            Duration.remove(p, 5);
            Duration.put(p, 5);
            AtomicReference<BukkitTask> task = new AtomicReference<>();

            if(PlayerStatus.getStatus(p.getUniqueId()) == PlayerStatus.INGAME) {

                String teamName = TeamsManager.getTeam(p.getUniqueId()).getTeamName();


                Location loc = Config.getLocation("Teams.Base." + teamName + ".Spawn");
                if (loc != null) {
                    PlayerMoveEvent.players.add(p);

                    p.getInventory().clear();
                    p.teleport(loc);
                    p.setFlySpeed(0);
                    p.setWalkSpeed(0);
                    p.setGameMode(GameMode.ADVENTURE);
                    Tablist.updateTablist(p);
                    ItemsManager.giveItem(p);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 250));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 250));



                    if (round == 0) {
                        p.sendTitle("", "§7Lancement de la partie");
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HAT, 10, 1);
                    }


                    task.set(Bukkit.getScheduler().runTaskTimer(Main.getInstance, () -> {

                        if(Duration.get(p) == null) {
                            return;
                        }
                            int i = Duration.get(p);

                            if (i  >= 1) {
                                p.sendTitle("", "§d" + Duration.get(p) + "sec");
                                p.teleport(loc);
                                Integer i1 = Duration.get(p);
                                Duration.put(p, i1 - 1);

                            } else {

                                p.removePotionEffect(PotionEffectType.JUMP);
                                p.setWalkSpeed(0.2f);
                                p.setFlySpeed(0.2f);
                                p.getActivePotionEffects().clear();
                                p.removePotionEffect(PotionEffectType.SLOW);
                                p.removePotionEffect(PotionEffectType.REGENERATION);
                                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE );
                                p.setGameMode(GameMode.SURVIVAL);
                                p.teleport(loc);

                                p.sendTitle("", "§7C'est parti !");
                                PlayerMoveEvent.players.remove(p);
                                Duration.remove(p);
                                task.get().cancel();

                            }

                            // Ton code à exécuter ici

                        }, 20L, 20L));


                    ServerStatus.setStatus(ServerStatus.IN_GAME);

                } else {
                    System.out.println("La location n'a pas été configurée pour cette équipe : " + teamName);
                }
            }

    }
}
