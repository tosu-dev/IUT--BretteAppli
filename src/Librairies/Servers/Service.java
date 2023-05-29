package Librairies.Servers;


import Librairies.Communication.Protocol;
import Librairies.Communication.WankaProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;

public abstract class Service extends Link {

    public Service(Socket socket, Class<? extends Protocol> protocol) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(socket, protocol);
    }

    public Service(Socket socket) throws IOException {
        super(socket);
    }

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

    public void close() throws IOException {
        this.client.close();
        this.protocol.close();
    }

    public void start() {
        (new Thread(this)).start();
    }
}