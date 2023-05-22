package Librairies.Servers;


import Librairies.Communication.Protocol;
import Librairies.Communication.WankaProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;

public abstract class Service implements Runnable {

    protected final Socket   client;
    protected final Protocol protocol;

    public Service(Socket socket) throws IOException {
        this.client = socket;
        this.protocol = new WankaProtocol(this.client);
    }

    public Service(Socket socket, Class<? extends Protocol> protocol) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        this.client = socket;
        this.protocol = protocol.getConstructor(Socket.class).newInstance(socket);

    }

    protected Socket getClient() {
        return this.client;
    }

    protected Protocol getProtocol() {
        return this.protocol;
    }

    abstract protected void execute() throws IOException;

    @Override
    public void run() {
        try {
            while (true) {
                //todo see if we can remove this while true
                this.protocol.setupCommunication();
                execute();
            }
        } catch (SocketException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        (new Thread(this)).start();
    }
}