package fun.slowfeew.multibrain.Game;

import fun.slowfeew.multibrain.Game.ServerStatus;

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