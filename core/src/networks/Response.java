package networks;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private static final HashMap<Integer, String> CODES = new HashMap<Integer, String>() {{
        put(200, "OK");
        put(404, "Not Found");
        put(201, "Created");
        put(400, "Bad Request");
        put(403, "Forbidden");
    }};

    private String version;
    private int statusCode;
    private String phrase;
    private HashMap<String, String> headers;
    private String body;

    public Response(Request request) {
        this();
        this.version = request.getVersion();
        this.headers = request.getHeader();
        this.headers.put("Connection","closed");
        this.body = "";
    }

    public Response() {
        headers = new HashMap<>();
        headers.put("Connection", "closed");
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        phrase = CODES.get(statusCode);
    }

    public String getPhrase() {
        return phrase;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public void removeHeader(String name) {
        headers.remove(name);
    }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
        headers.put("Content-Length", Integer.toString(body.length()));
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(version)
                .append(" ")
                .append(statusCode)
                .append(" ")
                .append(phrase)
                .append("\r\n");

        for (Map.Entry<String, String> header : headers.entrySet()) {
            buf.append(header.getKey())
                    .append(": ")
                    .append(header.getValue())
                    .append("\r\n");
        }

        buf.append("\r\n")
                .append(body);

        return buf.toString();
    }
}
