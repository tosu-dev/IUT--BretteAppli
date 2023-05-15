package App.Server.Services;

import java.net.Socket;

public abstract class Service implements Runnable {

    protected final Socket client;

    public Service(Socket socket) {
		client = socket;
	}

    public void start() {
		new Thread(this).start();
    }
}
