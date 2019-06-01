package com.neo4j.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @RequestMapping(path = "/do")
    public void insert(String path, String username, String password, String db) throws IOException, InterruptedException {
        String exe = "python";
        String command = "F:\\asdf\\code\\pysql100.py";
        String[] cmdArr = new String[]{exe, command, username, password, db, path};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);

    }
}

