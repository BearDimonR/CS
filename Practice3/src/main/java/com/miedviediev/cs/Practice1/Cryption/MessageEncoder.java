package com.miedviediev.cs.Practice1.Cryption;

import com.miedviediev.cs.Practice1.CRC16;
import com.miedviediev.cs.Practice1.PackageInfo;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.*;

public class MessageEncoder {

    public static final byte bMagic = Integer.valueOf(19).byteValue();

    public static final String ALGORITHM = "AES";
    public static final String ALGORITHMPAIR = "RSA";
    private static MessageEncoder instance;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private SecretKey encoderKey;
    private SecretKey decoderKey;

    public synchronized static MessageEncoder getInstance() {
        if(instance == null)
            init();
        return instance;
    }

    private static void init() {
        try {
            instance = new MessageEncoder();
            Cipher cipher = Cipher.getInstance(ALGORITHMPAIR);
            cipher.init(Cipher.DECRYPT_MODE, MessageEncoder.getInstance().privateKey);
            byte[] data = cipher.doFinal(MessageDecoder.getInstance().getSecretKey());
            MessageEncoder.getInstance().decoderKey = new SecretKeySpec(data, 0, data.length, ALGORITHM);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private MessageEncoder() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHMPAIR);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            int keyBitSize = 256;
            keyGenerator.init(keyBitSize, new SecureRandom());
            encoderKey = keyGenerator.generateKey();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public byte[] getEncoderKey() {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHMPAIR);
            cipher.init(Cipher.ENCRYPT_MODE, MessageDecoder.getInstance().getPublicKey());
            return cipher.doFinal(encoderKey.getEncoded());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public byte[] encodePackage(PackageInfo pkg) {
        try {
            byte[] msg = encodeMessage(pkg);
            ByteBuffer buffer = ByteBuffer.allocate(msg.length + 18);
            buffer.put(bMagic);
            buffer.put(pkg.getbSrc());
            buffer.putLong(pkg.getbPktId());
            buffer.putInt(msg.length);
            byte[] check = new byte[14];
            buffer.position(0);
            buffer.get(check,0, 14);
            buffer.putShort(CRC16.calc(check));
            buffer.put(msg);
            buffer.putShort(CRC16.calc(msg));
            return buffer.array();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private byte[] encodeMessage(PackageInfo pkg) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, encoderKey);
        byte[] msg = cipher.doFinal(pkg.getMessage().getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, decoderKey);
        msg = cipher.doFinal(msg);
        ByteBuffer buffer = ByteBuffer.allocate(msg.length + 8);
        buffer.putInt(pkg.getcType());
        buffer.putInt(pkg.getbUserId());
        buffer.put(msg);
        return buffer.array();
    }


}
