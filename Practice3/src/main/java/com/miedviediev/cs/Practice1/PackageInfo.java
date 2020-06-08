package com.miedviediev.cs.Practice1;

import java.io.Serializable;

public class PackageInfo implements Serializable {
    private static long counter;
    public static long nextNbr() { return ++counter; }

    private long bPktId;
    private int cType;
    private int bUserId;
    private byte bSrc;
    private String message;

    public long getbPktId() {
        return bPktId;
    }

    public int getcType() {
        return cType;
    }

    public int getbUserId() {
        return bUserId;
    }

    public String getMessage() {
        return message;
    }

    public byte getbSrc() {
        return bSrc;
    }

    public PackageInfo(long bPktId , byte bSrc, int cType, int bUserId, String message) {
        this.bPktId = bPktId;
        this.bSrc = bSrc;
        this.bUserId = bUserId;
        this.cType = cType;
        this.message = message;
    }

    public PackageInfo(byte bSrc, int cType, int bUserId, String message) {
        bPktId = PackageInfo.nextNbr();
        this.bSrc = bSrc;
        this.bUserId = bUserId;
        this.cType = cType;
        this.message = message;
    }

    @Override
    public String toString() {
        return "PackageInfo{" +
                "bPktId=" + bPktId +
                ", cType=" + cType +
                ", bUserId=" + bUserId +
                ", bSrc=" + bSrc +
                ",\n message='" + message + '\'' +
                '}';
    }
}
