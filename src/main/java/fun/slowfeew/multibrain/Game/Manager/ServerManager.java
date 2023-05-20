package fun.slowfeew.multibrain.Game.Manager;

import fun.slowfeew.multibrain.Game.Enum.ServerStatus;

public class ServerManager {

    private ServerStatus serverStatus;

    public ServerManager() {
        this.serverStatus = ServerStatus.STARTING;  // initial status
    }

    public ServerStatus getStatus() {
        return this.serverStatus;
    }

    public void setStatus(ServerStatus status) {
        this.serverStatus = status;
    }
}