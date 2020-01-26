package networks;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Request {
    private String method;
    private String URL;
    private String version;
    private HashMap<String, String> headers;
    private String body;

    public Request() {
        headers = new HashMap<>();
        body = "";
        method = "";
        URL = "";
        version = "HTTP/1.0";

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public void removeHeader(String name) {
        headers.remove(name);
    }

    public HashMap<String, String> getHeader() {
        HashMap<String, String> copy = new HashMap<>();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            copy.put(header.getKey(), header.getValue());
        }
        return copy;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public boolean headerContains(String name) {
        return headers.containsKey(name);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        buffer.append(method)
                .append(" ")
                .append(URL)
                .append(" ")
                .append(version)
                .append("\r\n");
        for (String header : headers.keySet()) {
            buffer.append(header)
                    .append(": ")
                    .append(headers.get(header))
                    .append("\r\n");
        }

        buffer.append("\r\n")
                .append(body);

        return buffer.toString();
    }

    public void fromString(String raw) {

        String[] list = raw.split("\r\n\r\n");
        String[] upper_portion = list[0].split("\r\n");
        if (list.length > 1) {
            body = list[1];
        }
        String method_line = upper_portion[0];
        StringTokenizer token = new StringTokenizer(method_line);
        method = token.nextToken(" ");
        URL = token.nextToken(" ");
        version = token.nextToken(" ");

        for (int i = 1; i < upper_portion.length; i++) {
            String[] header = upper_portion[i].split(": ");
            headers.put(header[0], header[1]);
        }

    }


}

