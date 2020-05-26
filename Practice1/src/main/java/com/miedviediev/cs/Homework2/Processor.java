package com.miedviediev.cs.Homework2;

import com.miedviediev.cs.Homework2.Commands.ECommands;
import com.miedviediev.cs.Practice1.MessageEncoder;
import com.miedviediev.cs.Practice1.PackageInfo;

public class Processor {

    private static Processor processor;

    public static Processor getInstance() {
        if(processor == null)
            processor = new Processor();
        return processor;
    }

    private Processor() {}

    public byte[] process(PackageInfo packageInfo) {
        String[] msg = packageInfo.getMessage().split(",");
        return MessageEncoder.getInstance().encodePackage(
                new PackageInfo(packageInfo.getbSrc(), packageInfo.getcType(), packageInfo.getbUserId(),
                    ECommands.getCommandById(packageInfo.getcType()).execute(msg)));
    }

}
