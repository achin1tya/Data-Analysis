import java.io.IOException;

import de.redsix.pdfcompare.PdfComparator;


public class Test1 {
	public static void main(String[] args) throws IOException {
		String file1="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Light Motor Vehicle Driver.pdf";
		String file2="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf";
		String resultFile="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files";
		new PdfComparator(file1, file2).compare().writeTo(resultFile);
		System.out.println("Process Completed");
			
	}
}
