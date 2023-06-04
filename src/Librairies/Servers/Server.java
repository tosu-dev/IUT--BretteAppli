package Librairies.Servers;

import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.lang.reflect.InvocationTargetException;

public abstract class Server implements Runnable {

    private final int                      port;
    private final String                   name;
    private final ServerSocket             listenSocket;
    private final Class<? extends Service> serviceClass;
    private       Service                  actualServiceClass;

    public Server(Class<? extends Service> serviceClass, int port, String name) throws IOException {
        this.serviceClass = serviceClass;
        this.listenSocket = new ServerSocket(port);
        this.actualServiceClass = null;
        this.port = port;
        this.name = name;
    }

    public Server(Class<? extends Service> serviceClass, int port) throws IOException {
        this(serviceClass, port, "Not defined");
    }

    public String getName() {
        return this.name;
    }

    public String getIp() {
        return "127.0.0.1:" + port;
    }

    public ServerSocket getListenSocket() {
        return listenSocket;
    }

    public void run() {
        try {
            while (true) {
                actualServiceClass = this.serviceClass.getConstructor(Socket.class).newInstance(listenSocket.accept());
                actualServiceClass.start();
            }
        } catch (NoSuchMethodException e) {
            try {
                this.listenSocket.close();
            } catch (IOException ignored) {
            }

            System.err.println("Problem on the listening port : " + e);

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() throws IOException {
        if (this.actualServiceClass != null) {
            this.actualServiceClass.close();
        }
        this.getListenSocket().close();
    }
}