package networks.commands;

import networks.Request;
import networks.Response;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class Get implements Command {

    @Override

    public void exec(Request request, Response response) {
        File file = new File(SRC_PATH + request.getURL());

        if (!file.exists()) {
            response.setStatusCode(404);
            return;
        }

        if (file.isDirectory()) {
            handleDir(file, response);
        } else {
            handleFile(file, response);
        }
        response.addHeader("Content-Type","application/json");
    }


    private void handleDir(File file, Response response) {
        StringBuilder builder = new StringBuilder("{\"directory\":[ ");
        String[] list = file.list();
        for(int i = 0; i < list.length; i++) {
            builder.append("\"")
                    .append(list[i]).append("\" ");
            if(i != list.length - 1) {
                builder.append(",");
            } else {
                builder.append("]}");
            }

        }

        response.setBody(builder.toString());
        response.setStatusCode(200);
    }

    private void handleFile(File file, Response response) {

        StringBuilder builder = new StringBuilder();
        try {

            BufferedReader in = new BufferedReader(new FileReader(file));
            int character;

            while ((character = in.read()) != -1) {
                builder.append((char) character);
            }

            response.setStatusCode(200);
            JSONObject obj = new JSONObject();
            obj.put("data",builder.toString());
            String temp = obj.toJSONString();
            response.setBody(temp);

        } catch (IOException e) {
            response.setStatusCode(404);
        }
    }
}
