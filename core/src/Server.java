import networks.Session;
import networks.commands.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get(Command.SRC_PATH));
            Files.createDirectories(Paths.get(Command.OUT_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ServerSocket server = new ServerSocket(8888);
            while (true) {
                Socket client = server.accept();
                Thread thread = new Thread(new Session(client));
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Error opening socket on port 8888");
        }
    }
}
