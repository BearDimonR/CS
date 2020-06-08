package ua.com.ke4a_store.network.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static java.nio.charset.StandardCharsets.UTF_16BE;

public class Decriptor {
    private static volatile Decriptor instance;
    private Cipher cipher;
    private Key key;

    private Decriptor() {
        try {
            byte[] key_b = "Hi_iAm_IhoR'sKey".getBytes(UTF_16BE);
            this.key = new SecretKeySpec(key_b, "AES");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getDecrypted(byte[] input) {
        return CipherObject.getInstance().decrypt(input);
    }

    public static Decriptor getInstance() {
        Decriptor localInstance = instance;
        if (localInstance == null) {
            synchronized (Decriptor.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Decriptor();
                }
            }
        }
        return localInstance;
    }
}
