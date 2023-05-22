package Librairies.Servers;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;

public abstract class Server implements Runnable {
    private final int port;

    private final ServerSocket             listenSocket;
    private final Class<? extends Service> serviceClass;
    private       Service                  actualServiceClass;

    public Server(Class<? extends Service> serviceClass, int port) throws IOException {
        this.port = port;
        this.serviceClass = serviceClass;
        this.listenSocket = new ServerSocket(this.port);
        this.actualServiceClass = null;
    }

    public int getPort() {
        return this.port;
    }

    public ServerSocket getListenSocket() {
        return listenSocket;
    }

    public void run() {
        try {
            while (true) {
                //todo see if we can remove this while true
                actualServiceClass = this.serviceClass.getConstructor(Socket.class).newInstance(listenSocket.accept());
                actualServiceClass.start();
            }
        } catch (NoSuchMethodException e) {
            try {
                this.listenSocket.close();
            } catch (IOException ignored) {}

            System.err.println("Problem on the listening port : " + e);

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws IOException {
        if (this.actualServiceClass != null) {
            this.actualServiceClass.getClient().close();
        }
        this.getListenSocket().close();
    }
}