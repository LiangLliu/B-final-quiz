package com.example.demo.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ParseFile {

    private static String readTxt(File file) throws IOException {
        String result;
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        StringBuilder content = new StringBuilder();
        while ((result = br.readLine()) != null) {
            content.append(result).append(" ");
        }
        return content.toString();
    }

    public static List<String> getStudentName(String fileName) throws IOException {
        String result = readTxt(ResourceUtils.getFile(fileName));
        return Arrays.asList(result.split(" "));
    }

}
