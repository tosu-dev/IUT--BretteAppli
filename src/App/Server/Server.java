package App.Server;

import java.io.IOException;
import java.net.*;

public abstract class Server implements Runnable {

    protected final ServerSocket socket;

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
    }

    public void finalize() throws Throwable {
		try {
            socket.close();
        } catch (IOException e) { ; }
	}

}
