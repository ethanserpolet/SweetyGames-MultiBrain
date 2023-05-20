package fun.slowfeew.multibrain.Game.Enum;

public enum ServerStatus {
    STARTING, WAITING, IN_GAME, ROUNDUP, ENDING, STOPPING;


    private static ServerStatus status;


    public static void setStatus(ServerStatus sstatus) {
        status = sstatus;
    }

    public static ServerStatus getStatus() {
        return status;

    }

    public static boolean isStatus(ServerStatus sstatus) {
        return status == sstatus;
    }

}