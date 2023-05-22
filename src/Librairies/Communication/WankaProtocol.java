package Librairies.Communication;

import java.io.IOException;
import java.net.Socket;

public class WankaProtocol extends Protocol {

    private final String LINE_BREAK = "##";

    public WankaProtocol(Socket socket) throws IOException {
        super(socket);
    }

    public String encode(String data) {
        if (data != null) {
            return data.replaceAll(System.lineSeparator(), LINE_BREAK);
        }
        return "";
    }

    public String decode(String data) {
        if (data != null) {
            return data.replaceAll(LINE_BREAK, System.lineSeparator());
        }
        return null;
    }
}
