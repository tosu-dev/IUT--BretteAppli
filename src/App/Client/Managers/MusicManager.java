package App.Client.Managers;

import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;

public class MusicManager {
    private static Player player;
    private static Thread musicThread;

    public static void play() {

        try {
            FileInputStream fileInputStream = new FileInputStream(MusicManager.class.getResource("../Vendors/rickroll.mp3").getFile());
            MusicManager.player = new Player(fileInputStream);
            musicThread = new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            musicThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (MusicManager.musicThread != null) {
            MusicManager.player.close();
            musicThread.interrupt();
        }
    }
}
