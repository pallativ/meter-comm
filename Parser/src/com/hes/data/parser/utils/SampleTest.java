package com.hes.data.parser.utils;

import java.util.List;

import com.hes.data.parser.process.DataProcessorAction;

public class SampleTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            String str = "00 01 00 01 00 01 00 e0 c4 01 c1 00 01 0c 02 04 12 00 08 09 06 00 00 01 00 00 ff 0f 02 12 00 00 02 04 12 00 01 09 06 00 00 60 0a 02 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 01 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 02 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 03 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 04 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 09 08 00 ff 0f 02 12 00 00 02 04 12 00 03 09 06 01 00 0a 08 00 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 09 06 00 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 09 06 00 ff 0f 05 12 00 00 02 04 12 00 04 09 06 01 00 0a 06 00 ff 0f 02 12 00 00 02 04 12 00 04 09 06 01 00 0a 06 00 ff 0f 05 12 00 00 ff ff ";
            List<String> obisHeaderList = new DataProcessorAction().processObisHeaderData(str);
            System.out.println(obisHeaderList.size());

            str = "00 01 00 01 00 01 04 02 c4 02 c1 00 00 00 00 01 00 82 03 f4 01 79 02 0c 09 0c 08 1d 04 17 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 07 14 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 07 15 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 09 16 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 09 17 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 09 18 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 0b 06 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 0b 07 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 0b 08 ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 0b 09 ff 00 00 00 00 fe a7 00 11 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 0b 0a ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff 09 02 0c 09 0c 08 1d 0b 0b ff 00 00 00 00 fe a7 00 11 80 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 06 00 00 00 00 09 0c ff ff ff ff ff ff ff ff 00 ff ff ";
            obisHeaderList = new DataProcessorAction().processBillObisData(str);
            System.out.println(obisHeaderList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
