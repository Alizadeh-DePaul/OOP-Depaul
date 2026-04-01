/*
 *
 *  *
 *  *  * Copyright (c) 2024.
 *  *  * Vahid Alizadeh
 *  *  * Object-oriented Software Development
 *  *  * DePaul University
 *  *
 *
 */

package DesignPatterns.Adapter.TextFormatter;

public class CsvFormatter implements CsvFormattable{
    @Override
    public String formatCsvText(String text){
        String formattedText=text.replace(".",",");
        return formattedText;
    }
}
