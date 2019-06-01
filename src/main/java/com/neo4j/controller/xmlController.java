package com.neo4j.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: neo4j
 * @description:
 * @author: zzt_NJUST
 * @create: 2019-05-24 14:02
 **/
@RestController
@RequestMapping("/xml")
public class xmlController {

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(String num1,String num2,String num3) throws IOException, InterruptedException {
        String exe = "python";
        String command = "F:\\asdf\\code\\read.py";
        String[] cmdArr = new String[] {exe, command, num1,num2,num3};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
        return "sucesss";
    }
}
