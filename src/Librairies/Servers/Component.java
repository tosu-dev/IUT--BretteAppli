package Librairies.Servers;

import java.io.IOException;

public interface Component {

    void call(Service service) throws IOException;

}
