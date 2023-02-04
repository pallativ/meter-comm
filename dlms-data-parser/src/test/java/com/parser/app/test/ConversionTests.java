package com.parser.app.test;

import com.parser.app.utils.DlmsObjectConvert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConversionTests {
    @Test
    public void convertNegative(){
        var value = "FF FF FE 58";
        var result =   DlmsObjectConvert.ToLong(value.split(" "));
        assertEquals(-424, result);
        result = DlmsObjectConvert.ToLong("ff fe 3b 55".split(" "));
        assertEquals(-4696, result);
    }
}
