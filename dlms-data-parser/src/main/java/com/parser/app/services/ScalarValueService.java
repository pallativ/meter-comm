package com.parser.app.services;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ScalarValueService {
    public static Map<String, String> read() {
        Map<String, String> map = new HashMap<>();
        try {

            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:scalar.MDV");
//            File configFilePath = ResourceUtils.getFile("classpath:scalar.MDV");
//            if (!configFilePath.exists()) {
//                throw new Exception("Scalar MDV file doesn't exists");
//            }

            List<String> dataList = getAllLines(resource);//FileUtils.readLines(configFilePath);

            for (String str : dataList) {
                try {
                    String[] strArr = str.split("=");
                    if (strArr.length == 2) {
                        map.put(strArr[0], strArr[1].replace(" ", "").replace("\n", ""));
                    }
                } catch (Exception e) {
                    throw new Exception("Error Reading the scalar values.", e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List<String> getAllLines(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return List.of(FileCopyUtils.copyToString(reader).split("\n"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
