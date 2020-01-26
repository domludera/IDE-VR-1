package networks;

import networks.commands.Command;

import java.io.*;
import java.net.Socket;

public class Session implements Runnable {
    private Socket client;

    public Session(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            StringBuilder builder = new StringBuilder();

            do {

                builder.append((char) in.read());
            } while (builder.indexOf("\r\n\r\n") == -1);


            Request request = new Request();
            request.fromString(builder.toString());
            builder = new StringBuilder();
            int length = 0;
            if (request.headerContains("Content-Length")) {
                length = Integer.parseInt(request.getHeader("Content-Length"));
            }


            while (length > 0) {
                builder.append((char) in.read());
                length--;
            }


            request.setBody(builder.toString());

            Response response = Command.match(request);
            out.write(response.toString());
            out.flush();
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
