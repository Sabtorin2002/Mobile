package com.example.r2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

public class HttpsManager {
    private String adresaUrl;
    private BufferedReader bufferedReader;
    private HttpsURLConnection conexiune;

    public HttpsManager(String adresaUrl) {
        this.adresaUrl = adresaUrl;
    }

    public String process() {

        try {
            return getRezultatHttps();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inchidereConexiune();
        }

        return null;
    }

    private void inchidereConexiune() {

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conexiune.disconnect();
    }

    private String getRezultatHttps() throws IOException {
        trustEveryone();
        conexiune = (HttpsURLConnection) new URL(adresaUrl).openConnection();
        bufferedReader = new BufferedReader(new InputStreamReader(conexiune.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String linie;
        while ((linie = bufferedReader.readLine()) != null) {
            sb.append(linie);
        }

        return sb.toString();
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
