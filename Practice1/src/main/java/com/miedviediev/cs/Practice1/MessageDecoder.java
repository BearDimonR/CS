package com.miedviediev.cs.Practice1;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.*;

public class MessageDecoder {

    private static final String ALGORITHM = MessageEncoder.ALGORITHM;
    private static final String ALGORITHMPAIR = MessageEncoder.ALGORITHMPAIR;
    private static MessageDecoder instance;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    private SecretKey secretKey;
    private SecretKey encoderKey;

    private static final byte bMagic = Integer.valueOf(19).byteValue();

    public static MessageDecoder getInstance() {
        if(instance == null)
            init();
        return instance;
    }

    private static void init() {
        try {
            instance = new MessageDecoder();
            Cipher cipher = Cipher.getInstance(ALGORITHMPAIR);
            cipher.init(Cipher.DECRYPT_MODE, MessageDecoder.getInstance().privateKey);
            byte[] data = cipher.doFinal(MessageEncoder.getInstance().getEncoderKey());
            MessageDecoder.getInstance().encoderKey = new SecretKeySpec(data, 0, data.length, ALGORITHM);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private MessageDecoder() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHMPAIR);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            int keyBitSize = 256;
            keyGenerator.init(keyBitSize, new SecureRandom());
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }

    public byte[] getSecretKey() {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHMPAIR);
            cipher.init(Cipher.ENCRYPT_MODE, MessageEncoder.getInstance().getPublicKey());
            return cipher.doFinal(secretKey.getEncoded());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PackageInfo decodePackage(byte[] pack) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(pack);
            while(buffer.get() != bMagic);
            buffer.position(buffer.position() - 1);
            byte[] check = new byte[14];
            buffer.get(check,0, 14);
            if(buffer.getShort() != CRC16.calc(check))
                throw new Exception("CRC16 is different");
            buffer.position(buffer.position() - 15);
            byte bSrc = buffer.get();
            long bPktId = buffer.getLong();
            int msgLen = buffer.getInt();
            buffer.position(buffer.position() + 2);
            check = new byte[msgLen];
            buffer.get(check, 0, msgLen);
            if(buffer.getShort() != CRC16.calc(check))
                throw new Exception("CRC16 is different");
            buffer.position(buffer.position() - msgLen - 2);
            int cType = buffer.getInt();
            int bUserId = buffer.getInt();
            check = new byte[msgLen - 8];
            buffer.get(check, 0, msgLen - 8);
            return new PackageInfo(bPktId, bSrc, cType, bUserId, decodeMessage(check));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private String decodeMessage(byte[] msg) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] res = cipher.doFinal(msg);
        cipher.init(Cipher.DECRYPT_MODE, encoderKey);
        return new String(cipher.doFinal(res));
    }

}
