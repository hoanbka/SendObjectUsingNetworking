package transfer.object;


import java.io.Serializable;

public class NetworkingObject implements Serializable {
    private String ip;
    private int port;

    public NetworkingObject(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String toString() {
        return "{ ip: " + this.ip + ";" + "port: " + this.port + "}";
    }
}
