package com.test;


import java.util.Random;

public class DbTestMain {
    private static Random r = new Random();

            /*+-------+-------------+------+-----+---------+-------+
            | Field | Type        | Null | Key | Default | Extra |
            +-------+-------------+------+-----+---------+-------+
            | a     | int(11)     | NO   | PRI | NULL    |       |
            | b     | varchar(16) | YES  |     | NULL    |       |
            +-------+-------------+------+-----+---------+-------+*/
    public static void main(String[] args) {
        DbTestMain main = new DbTestMain();
        main.genData(100, 16);
    }

    private void genData(int count, int clen) {
        for (int i = 0; i <count ; i++) {
            System.out.printf("%d,%s\n" , i , getStr(clen));
        }
    }

    private Object getStr(int clen) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < clen; i++) {
            sb.append((char)('a' + r.nextInt(24)));
        }
        return sb.toString();
    }
}
