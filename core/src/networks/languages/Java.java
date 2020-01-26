package networks.languages;

import java.nio.file.Path;

public class Java implements Language {

    @Override
    public String compile(Path in, Path out) {
        String fileName = in.toString();

        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        fileName = fileName.replace(".java", "");

        return "javac " + in.toString() + " -d " + out.toString() + " && java " + fileName;
    }
}
