package com.neo4j.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: enginegraph
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-05-24 10:23
 **/
public class Csv {
    public static void main(String[] args) throws IOException, InterruptedException {
        String exe = "python";
        String command = "F:\\asdf\\pysql100.py";
        String path = "F:/neo4j/neo4j-community-3.5.3/import/";
        //D:/neo4j-community-3.5.4-windows/neo4j-community-3.5.4/import/
        String username = "root";
        String password = "root";
        String db = "mmall";
        String[] cmdArr = new String[]{exe, command, username, password, db, path};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
    }
}