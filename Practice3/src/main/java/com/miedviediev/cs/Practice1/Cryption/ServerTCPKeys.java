package com.miedviediev.cs.Practice1.Cryption;

import com.miedviediev.cs.Practice1.CRC16;
import com.miedviediev.cs.Practice1.PackageInfo;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ServerTCPKeys {

    private static ServerTCPKeys instance;
    public synchronized static ServerTCPKeys getInstance() {
        if(instance == null)
            instance = new ServerTCPKeys();
        return instance;
    }

    public static final byte bMagic = Integer.valueOf(19).byteValue();
    public static final String ALGORITHM = "AES";
    public static final String ALGORITHMPAIR = "RSA";

    public HashMap<Socket, SecretKey> keySet;
    private SecretKey serverKey;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    private ServerTCPKeys() {
        keySet = new HashMap<>();
        KeyGenerator keyGenerator = null;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHMPAIR);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            int keyBitSize = 256;
            keyGenerator.init(keyBitSize, new SecureRandom());
            serverKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public byte[] getPublicKey() {
        return publicKey.getEncoded();
    }

    public byte[] getServerKey(PublicKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHMPAIR);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(serverKey.getEncoded());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public synchronized void addKey(Socket s, byte[] res) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHMPAIR);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] data = cipher.doFinal(res);
            SecretKey userKey = new SecretKeySpec(data, 0, data.length, ALGORITHM);
            keySet.put(s, userKey);
        } catch (Exception e) {
            System.err.println("Problem with adding key to key map");
        }
    }

    public byte[] encodePackage(Socket s, PackageInfo pkg) {
        try {
            byte[] msg = encodeMessage(s, pkg);
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

    private byte[] encodeMessage(Socket s, PackageInfo pkg) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySet.get(s));
        byte[] msg = cipher.doFinal(pkg.getMessage().getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, serverKey);
        msg = cipher.doFinal(msg);
        ByteBuffer buffer = ByteBuffer.allocate(msg.length + 8);
        buffer.putInt(pkg.getcType());
        buffer.putInt(pkg.getbUserId());
        buffer.put(msg);
        return buffer.array();
    }

    public PackageInfo decodePackage(Socket s, byte[] pack) {
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
            return new PackageInfo(bPktId, bSrc, cType, bUserId, decodeMessage(s, check));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private String decodeMessage(Socket s, byte[] msg) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, serverKey);
        byte[] res = cipher.doFinal(msg);
        cipher.init(Cipher.DECRYPT_MODE, keySet.get(s));
        return new String(cipher.doFinal(res));
    }

    public void checkKeys() {
        Set<Socket> remove = new HashSet<>();
        for (Socket s: keySet.keySet())
            if(s.isClosed())
                remove.add(s);
        for (Socket s: remove)
            keySet.remove(s);

    }
}
