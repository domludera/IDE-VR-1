package networks.languages;

import networks.Response;

import org.json.simple.JSONObject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public interface Language {
    String OUTPUT = "output.log";

    static Language match(String language) {
        if (language.equalsIgnoreCase("java")) {
            return new Java();
        }

        return null;
    }

    static void compile(Language language, Path in, Path out, Response response) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            processBuilder.command("bash", "-c", "cd " + out.toString() + " && " + language.compile(in, out) + " > output.log");
            Process process = processBuilder.start();
            process.waitFor();
            File f = new File(out.toString() + "/" + OUTPUT);

            BufferedReader reader = new BufferedReader(new FileReader(f));

            StringBuilder buf = new StringBuilder();
            int c;

            while ((c = reader.read()) != -1) {
                buf.append((char) c);
            }



            JSONObject obj = new JSONObject();
            obj.put("data",buf.toString());
            response.setBody(obj.toJSONString());

            response.setStatusCode(200);

        } catch (IOException | InterruptedException e) {
            response.setStatusCode(400);
        }
    }

    String compile(Path in, Path out);
}
