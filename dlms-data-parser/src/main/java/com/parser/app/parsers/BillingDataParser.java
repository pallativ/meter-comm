/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.parser.app.parsers;

import com.parser.app.models.BillingHistoryModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Veera
 */
public interface BillingDataParser {

    ArrayList<BillingHistoryModel> parse(String fileName) throws Exception;
}
