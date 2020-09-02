package com.softuni.json.utils;

import java.io.*;

public class FileIOUtilImpl implements FileIOUtil {


    @Override
    public String readFileContent(String filePath) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));

        StringBuilder sb = new StringBuilder();

        String line = "";

        while (null != (line =  br.readLine())){
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();

//        return Files.readAllLines(get(filePath))
//                .stream().filter(r -> !r.isEmpty())
//                .collect(Collectors.joining(System.lineSeparator()));
    }

}
