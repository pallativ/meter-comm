package com.parser.app.services;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScalarValueService {
    public static Map<String, String> read() {
        Map<String, String> map = new HashMap<>();
        try {
            File configFilePath = ResourceUtils.getFile("classpath:scalar.MDV");
            if (!configFilePath.exists()) {
                throw new Exception("Scalar MDV file doesn't exists");
            }

            List<String> dataList = FileUtils.readLines(configFilePath);

            for (String str : dataList) {
                try {
                    String[] strArr = str.split("=");
                    if(strArr.length == 2){
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
}
