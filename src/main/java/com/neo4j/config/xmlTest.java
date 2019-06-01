package com.neo4j.config;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-05-24 10:23
 **/
public class xmlTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String exe = "python";
        String command = "F:\\asdf\\read.py";
        String num1 = "F:\\asdf\\0404.xml";
        String num2 = "F:\\asdf\\log.txt";
        String num3 = "F:\\asdf\\tuples.txt";
        String[] cmdArr = new String[] {exe, command, num1,num2,num3};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
    }
}
