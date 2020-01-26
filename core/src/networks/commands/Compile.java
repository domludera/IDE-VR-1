package networks.commands;

import networks.Request;
import networks.Response;
import networks.languages.Language;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compile implements Command {


    @Override
    public void exec(Request request, Response response) {
        String path = request.getURL();
        String dir = path.substring(0, path.lastIndexOf("/") + 1);
        String lang = request.getHeader("Language");
        Path outPath = Paths.get(OUT_PATH + dir);
        Path inPath = Paths.get(SRC_PATH + path);

        if (!Files.exists(inPath)) {
            response.setStatusCode(404);
            return;
        }

        try {
            Files.createDirectories(outPath);
        } catch (IOException e) {
            response.setStatusCode(400);
            return;
        }


        Language language = Language.match(lang);

        if (language == null) {
            response.setStatusCode(400);
            return;
        }

        Language.compile(language, inPath, outPath, response);
    }
}
