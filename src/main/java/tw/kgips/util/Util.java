package tw.kgips.util;

import tw.kgips.sandbox.FutureExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Util {

    private Util() {
    }

    public static String getJSON(String url, int timeout) throws IOException {

        URL u = new URL(url);
        HttpURLConnection c = (HttpURLConnection) u.openConnection();

        c.setRequestMethod("GET");
        c.setUseCaches(false);
        c.setAllowUserInteraction(false);
        c.setConnectTimeout(timeout);
        c.setReadTimeout(timeout);
        c.setRequestProperty("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
        c.connect();

        int status = c.getResponseCode();

        switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                return sb.toString();
            default:
                throw new RuntimeException("fail to get the response");
        }

    }

    public static void setSSL() {
        String certFileName = "client.jks";
        ClassLoader classLoader = FutureExchange.class.getClassLoader();
        System.setProperty("javax.net.ssl.trustStore",
            Objects.requireNonNull(classLoader.getResource(certFileName)).getPath());
    }

}
