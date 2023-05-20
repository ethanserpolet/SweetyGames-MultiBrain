package fun.slowfeew.multibrain.Game.Manager;


import fun.slowfeew.multibrain.WorldManager.Config;
import org.bukkit.Location;

import java.util.*;

public enum TeamsManager {
    RED(1, "Red", "§cRouge"),
    BLUE(2, "Blue", "§9Bleu"),
    GREEN(3, "Green", "§aVert"),
    YELLOW(4, "Yellow", "§eJaune");

    private int order;
    private String teamName;
    private String prefix;
    private List<UUID> players;

    public static HashMap<TeamsManager, Integer> points = new HashMap<>();


    TeamsManager(int order, String teamName, String prefix) {
        this.order = order;
        this.teamName = teamName;
        this.prefix = prefix;
        this.players = new ArrayList<>();
    }

    public int getOrder() {
        return order;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPrefix() {
        return prefix;
    }

    public static int getSize(TeamsManager team) {
        return team.getPlayers().size();
    }
    public static void decreasePoints(TeamsManager team) {
        Integer currentPoints = points.get(team);
        if (currentPoints != null && currentPoints > 0) {
            points.put(team, currentPoints - 1);
        }
    }
    public void setTeam(UUID playerUUID) {
        for (TeamsManager team : values()) {
            team.removePlayer(playerUUID);
        }
        this.players.add(playerUUID);
    }

    public int getTeamSize() {
        return this.players.size();
    }

    public List<UUID> getPlayers() {
        return this.players;
    }

    public void removePlayer(UUID playerUUID) {
        this.players.remove(playerUUID);
    }

    public boolean hasPlayer(UUID playerUUID) {
        return this.players.contains(playerUUID);
    }

    public static Location getSpawn(TeamsManager team) {

        String loc = "Teams.Base." + team.getTeamName() + ".Spawn";
        return Config.getLocation(loc);
    }

    public static TeamsManager getTeamFromName(String teamName) {
        for (TeamsManager team : values()) {
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        return null; // or throw an exception, depending on your use case
    }

    public static List<UUID> getPlayers(TeamsManager team) {
        return Collections.unmodifiableList(team.getPlayers());
    }

    public static TeamsManager getTeam(UUID playerUUID) {
        for (TeamsManager team : values()) {
            if (team.hasPlayer(playerUUID)) {
                return team;
            }
        }
        return null; // or throw an exception, depending on your use case
    }

    public static boolean allTeamsHavePlayer() {
        for (TeamsManager team : values()) {
            if (team.getTeamSize() == 0) {
                return false;
            }
        }
        return true;
    }

    public static void fillEmptyTeamsWithPlayers(List<UUID> connectedPlayers) {
        List<TeamsManager> emptyTeams = new ArrayList<>();
        for (TeamsManager team : values()) {
            if (team.getTeamSize() == 0) {
                emptyTeams.add(team);
            }
        }

        if (emptyTeams.isEmpty()) {
            return;
        }

        Collections.shuffle(connectedPlayers);
        Iterator<UUID> playerIterator = connectedPlayers.iterator();

        for (TeamsManager team : emptyTeams) {
            if (!playerIterator.hasNext()) {
                break;
            }

            UUID playerUUID = playerIterator.next();
            team.setTeam(playerUUID);
        }
    }
}
