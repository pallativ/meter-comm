package com.hes.data.parser.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;


/**
 *
 * @author psvr
 *
 */
public class Utils {

    public Map<String, String> readProperties(String fileName) {
        Map<String, String> map = new HashMap<>();
        try {
            String workingDir = System.getProperty("user.dir");
            File configFilePath = new File(workingDir, fileName);
            if (!configFilePath.exists()) {
                configFilePath = new File("c:\\smartmeter\\" + fileName);
            }

            List<String> dataList = FileUtils.readLines(configFilePath);

            for (String str : dataList) {
                try {
                    String[] strArr = str.split("=");
                    map.put(strArr[0], strArr[1].replace(" ", "").replace("\n", ""));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
