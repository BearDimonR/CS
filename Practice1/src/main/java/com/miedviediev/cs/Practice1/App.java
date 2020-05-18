package com.miedviediev.cs.Practice1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App
{
    public static void main( String[] args )
    {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Your message: " + test(reader.readLine()));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public static String test(String input) {
        byte[] encodedMessage = MessageEncoder.getInstance().encodePackage(new PackageInfo(Integer.valueOf(5).byteValue(), 5, 2, input));
        return MessageDecoder.getInstance().decodePackage(encodedMessage).getMessage();
    }

}
