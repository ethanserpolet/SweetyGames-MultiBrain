package fun.slowfeew.multibrain.Game.Enum;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public enum PlayerStatus {
    INGAME, INSPEC, INMOD, INSPAWN;




    public static HashMap<UUID, PlayerStatus> playerStatus = new HashMap<>();

    private static PlayerStatus status;


    public static void setStatus(PlayerStatus sstatus, UUID id) {
        playerStatus.put(id, sstatus);

    }

    public static PlayerStatus getStatus(UUID id) {
        return playerStatus.get(id);
    }

    public static boolean isFull(PlayerStatus sstatus) {
        return Collections.frequency(playerStatus.values(), sstatus) == 4;
    }



}