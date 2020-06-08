package com.miedviediev.cs.Homework2;

import com.miedviediev.cs.Homework2.Commands.ECommands;
import com.miedviediev.cs.Homework2.Models.Storage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//public class App {
//    public static void main(String[] args) {
//        System.out.println("***** Storage info *****\n\n");
//        System.out.println(Storage.getInstance());
//        System.out.println("\n\n********* Start Processing *********\n");
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(System.in));
//
//        while(true) {
//            for (ECommands e: ECommands.values()) {
//                System.out.println(e.name() + " : " + e.getId());
//            }
//            try {
//                System.out.print("\n\nEnter command id: ");
//                int command = Integer.valueOf(reader.readLine());
//                System.out.print("Enter message(1,3): ");
//                String msg = reader.readLine();
//                System.out.print("How many packages: ");
//                int n = Integer.valueOf(reader.readLine());
//                FakeReceiver.getInstance().generateMsg(command, msg);
//                for(int i = 0; i < n; ++i)
//                    FakeReceiver.getInstance().receivePackage();
//                Thread.sleep(2000);
//                System.out.println("\n\nEnter to do it again, 0 to exit: \n\n");
//                if(reader.read() == '0') break;
//            } catch (Exception e) {
//                System.out.println("\nWrong values!\n\n");
//                continue;
//            }
//        }
//
//    }
//}
