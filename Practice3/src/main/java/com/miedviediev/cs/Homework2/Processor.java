package com.miedviediev.cs.Homework2;

import com.miedviediev.cs.Homework2.Commands.ECommands;
import com.miedviediev.cs.Practice1.Cryption.ServerTCPKeys;
import com.miedviediev.cs.Practice1.PackageInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidParameterException;
import java.sql.SQLException;

public class Processor {

    private static Processor processor;

    public static Processor getInstance() {
        if(processor == null)
            processor = new Processor();
        return processor;
    }

    private Processor() {}

    public PackageInfo process(PackageInfo packageInfo) {
        try {
            String[] msg = packageInfo.getMessage().split(",");
            return new PackageInfo(packageInfo.getbSrc(), packageInfo.getcType(), packageInfo.getbUserId(),
                    ECommands.getCommandById(packageInfo.getcType()).execute(msg));
        } catch (InvalidParameterException | SQLException e) {
            e.printStackTrace();
            return new PackageInfo(packageInfo.getbSrc(), packageInfo.getcType(), packageInfo.getbUserId(), "Fail");
        }
    }

}
