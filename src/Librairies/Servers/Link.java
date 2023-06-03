package Librairies.Servers;

import Librairies.Communication.Protocol;
import Librairies.Communication.WankaProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public abstract class Link implements Runnable {

    protected final Socket   client;
    protected final Protocol protocol;

    public Link(Socket socket) throws IOException {
        this.client = socket;
        this.protocol = new WankaProtocol(this.client);
    }

    public Link(Socket socket, Class<? extends Protocol> protocol) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.client = socket;
        this.protocol = protocol.getConstructor(Socket.class).newInstance(socket);
    }

    public String read() throws IOException {
        return this.protocol.read();
    }

    public void send(String data) {
        this.protocol.send(data);
    }

    public void run() {
    }

    public void close() throws IOException {
        this.client.close();
        this.protocol.close();
    }

    public void start() {
        (new Thread(this)).start();
    }

}
