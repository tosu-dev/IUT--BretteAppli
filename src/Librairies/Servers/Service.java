package Librairies.Servers;


import Librairies.Communication.Protocol;
import Librairies.Communication.WankaProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public abstract class Service extends Link {

    public Service(Socket socket, Class<? extends Protocol> protocol) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(socket, protocol);
    }

    public Service(Socket socket) throws IOException {
        super(socket);
    }

    protected abstract List<Class<? extends Component>> componentList();

    @Override
    public void run() {
        try {
            while (true) {
                this.protocol.setupCommunication();

                for(Class<? extends Component> component: this.componentList()) {
                    component.getConstructor().newInstance().call(this);
                }
            }
        } catch (SocketException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
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