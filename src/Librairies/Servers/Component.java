package Librairies.Servers;

public interface Component {

    default void call(Service service) {
        service.send("");
    };

}
