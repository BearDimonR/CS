package com.miedviediev.cs.Practice1.Cryption;

import com.miedviediev.cs.Practice1.CRC16;
import com.miedviediev.cs.Practice1.PackageInfo;

import java.nio.ByteBuffer;

public class UDPEncoding {

    private static UDPEncoding instance;

    public synchronized static UDPEncoding getInstance() {
        if (instance == null)
            instance = new UDPEncoding();
        return instance;
    }

    public static final byte bMagic = Integer.valueOf(19).byteValue();

    private UDPEncoding() {
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
            buffer.get(check, 0, 14);
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
        byte[] msg = pkg.getMessage().getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(msg.length + 8);
        buffer.putInt(pkg.getcType());
        buffer.putInt(pkg.getbUserId());
        buffer.put(msg);
        return buffer.array();
    }

    public PackageInfo decodePackage(byte[] pack) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(pack);
            while (buffer.get() != bMagic) ;
            buffer.position(buffer.position() - 1);
            byte[] check = new byte[14];
            buffer.get(check, 0, 14);
            if (buffer.getShort() != CRC16.calc(check))
                throw new Exception("CRC16 is different");
            buffer.position(buffer.position() - 15);
            byte bSrc = buffer.get();
            long bPktId = buffer.getLong();
            int msgLen = buffer.getInt();
            buffer.position(buffer.position() + 2);
            check = new byte[msgLen];
            buffer.get(check, 0, msgLen);
            if (buffer.getShort() != CRC16.calc(check))
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

    private String decodeMessage(byte[] msg) {
        return new String(msg);
    }
}
