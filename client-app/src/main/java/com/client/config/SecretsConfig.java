package com.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
@ConfigurationProperties(prefix = "server.ssl")
@Data
public class SecretsConfig {
    private Resource keyStore;
    private String keyStorePassword;
    private Resource trustStore;
    private String trustStorePassword;

    public KeyManager[] getKeyManagers() throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (InputStream stream = keyStore.getInputStream()) {
            ks.load(stream, keyStorePassword.toCharArray());
        }
        //KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(ks, keyStorePassword.toCharArray());
        return keyManagerFactory.getKeyManagers();
    }

    public TrustManager[] getTrustManagers() throws Exception {
        KeyStore ts = KeyStore.getInstance("PKCS12");
        //KeyStore ts  = KeyStore.getInstance(KeyStore.getDefaultType());
//        KeyStore ts = KeyStore.getInstance("JKS");
        try (InputStream stream = trustStore.getInputStream()) {
            ts.load(stream, trustStorePassword.toCharArray());
        }

        //TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(ts);
        return trustManagerFactory.getTrustManagers();
    }

//    https://4engineer.net/cipher/mutual-authentication-spring/
}

//public static SSLContext createServerSSLContext() throws Exception {
//    return SSLContexts.custom()
//            .loadTrustMaterial(SSLTestContexts.class.getResource("/test.keystore"),
//                    "nopassword".toCharArray())
//            .loadKeyMaterial(SSLTestContexts.class.getResource("/test.keystore"),
//                    "nopassword".toCharArray(), "nopassword".toCharArray())
//            .build();
//}
//
//public static SSLContext createClientSSLContext() throws Exception {
//    return SSLContexts.custom()
//            .loadTrustMaterial(SSLTestContexts.class.getResource("/test.keystore"),
//                    "nopassword".toCharArray())
//            .build();
//}