/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.parser.app.parsers;

import com.parser.app.models.BillingHistoryModel;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author Veera
 */
public interface BillingDataParser {

    public ArrayList<BillingHistoryModel> Parse(String fileName) throws IOException, Exception;
}
