package App.Server.Services.Borrow;

import Librairies.Servers.Server;

import java.io.IOException;

public class BorrowServer extends Server {

    private static final int                  PORT          = 1001;
    private static final String               NAME          = "Borrow";
    private static final Class<BorrowService> SERVICE_CLASS = BorrowService.class;


    public BorrowServer() throws IOException {
        super(SERVICE_CLASS, PORT, NAME);
    }
}
