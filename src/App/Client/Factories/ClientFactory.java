package App.Client.Factories;

import App.Client.Managers.ClientManager;
import App.Client.Managers.MusicManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ClientFactory {

    /**
     * Return Client Manager
     * -> if args contains only one argument, return client manager with selected port
     * -> if args contains two arguments, return client manager with selected host AND port
     * -> if we don't have args, return client manager with default host and port
     *
     * @param args array of arguments added to application startup (like host and port)
     * @return ClientManager, referred to arguments
     */
    public static ClientManager create(String[] args) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        int    DEFAULT_PORT = 1001;
        String DEFAULT_HOST = "localhost";

        ClientManager cm = switch (args.length) {
            case 1 -> new ClientManager(DEFAULT_HOST, Integer.parseInt(args[0]));
            case 2 -> new ClientManager(args[0], Integer.parseInt(args[1]));
            default -> new ClientManager(DEFAULT_HOST, DEFAULT_PORT);
        };

        System.out.println(cm.read());

        return cm;
    }

    /**
     * Method to read and show server messages and stop while needed.
     * @param cm global Client Manager
     */
    public static void manage(ClientManager cm) throws IOException {
        boolean sendMessage = false;

        while (true) {

            cm.send(sendMessage ? "" : cm.getInput().readLine());

             sendMessage = false;
            String response = cm.read();

            if (response.equals("stop")) break;

            if(response.startsWith("[STARTMUSIC]")) {
                MusicManager.play();
                response = response.replace("[STARTMUSIC]", "").trim();
            }
            if(response.startsWith("[STOPMUSIC]")) {
                MusicManager.stop();
                response = response.replace("[STOPMUSIC]", "").trim();
            }
            if(response.endsWith("[SEND]"))  {
                response = response.replace("[SEND]", "").trim();
                sendMessage = true;
            }
            System.out.println(response);

        }
    }

    /**
     * Close the actual client
     * @param cm Global Client Manager
     * @throws IOException Permit to the Client Thread to close. IT ALWAYS THROWS THIS EXCEPTION.
     */
    public static void close(ClientManager cm) throws IOException {
        cm.close();
    }

}
