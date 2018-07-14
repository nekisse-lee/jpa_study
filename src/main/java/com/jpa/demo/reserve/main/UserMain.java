package com.jpa.demo.reserve.main;

import com.jpa.demo.reserve.jpa.EMF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserMain {
    public static void main(String[] args) throws IOException {
        EMF.init();

        boolean start = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (start) {
            System.out.println("명령어를 입력하세요");
            String line = reader.readLine();
            String[] commands = line.split(" ");
            if (commands[0].equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                return;
            } else if (commands[0].equalsIgnoreCase("join")) {
            } else if (commands[0].equalsIgnoreCase("view")) {
            } else if (commands[0].equalsIgnoreCase("list")) {
            } else if (commands[0].equalsIgnoreCase("changename")) {
            } else if (commands[0].equalsIgnoreCase("withdraw")) {
            } else {
                System.out.println("올바른 명령어를 입력하세요.");
            }
            System.out.println("----");
        }
        EMF.close();
    }
}