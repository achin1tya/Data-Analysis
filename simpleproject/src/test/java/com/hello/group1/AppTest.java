package com.hello.group1;

import static org.junit.Assert.assertTrue;
import
import org.junit.Test;
import de.redsix.pdfcompare.PdfComparator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    public static void main(String[] args) {
        String file1="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Light Motor Vehicle Driver.pdf";
        String file2="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf";
        String resultFile="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\result";
        new PdfComparator(file1, file2).compare().writeTo(resultFile);
        System.out.println("Process Completed");

    }
}
