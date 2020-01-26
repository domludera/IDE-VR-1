package networks.commands;

import networks.Request;
import networks.Response;

public interface Command {
    String SRC_PATH = System.getProperty("user.home") + "/Documents/Core-Server/src";
    String OUT_PATH = System.getProperty("user.home") + "/Documents/Core-Server/out";

    static Response match(Request request) {
        Response response = new Response(request);
        response.removeHeader("Transfer-Encoding");
        Command command = null;

        if (request.getURL().contains("..")) {
            response.setStatusCode(403);
            return response;
        }

        if (request.getMethod().equalsIgnoreCase("GET")) {
            command = new Get();
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            if (!request.headerContains("Language")) {
                command = new Post();
            } else {
                command = new Compile();
            }
        }

        if (command == null) {
            response.setStatusCode(400);
            return response;
        }

        command.exec(request, response);
        return response;
    }

    void exec(Request request, Response response);
}
