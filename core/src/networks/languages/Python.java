package networks.languages;

import java.nio.file.Path;

public class Python implements Language {
    @Override
    public String compile(Path in, Path out) {
        String fileName = in.toString();
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);

        return "cp " + in.toString() + " . && python3 " + out.toString() + "/" + fileName;
    }
}
