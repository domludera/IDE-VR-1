package networks.commands;

import networks.Request;
import networks.Response;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Post implements Command {

    @Override

    public void exec(Request request, Response response) {
        String dir = request.getURL();
        dir = dir.substring(0,dir.lastIndexOf("/"));
        dir = SRC_PATH + dir;
        try {
            Files.createDirectories(Paths.get(dir));
        } catch (IOException e) {
            response.setStatusCode(400);
            return;
        }

        handleFile(request, response);
    }

    private void handleFile(Request request, Response response) {
        File file = new File(SRC_PATH + request.getURL());

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(request.getBody());
            out.flush();
        } catch (IOException e) {
            response.setStatusCode(403);
        }

        response.setStatusCode(201);
    }
}
