import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * Example program to verify the setup of pdfbox libraries
 */
public final class HelperMain
{

    /**
     * Print text present in the document
     */
    public static void main( String[] args ) throws IOException
    {
        String fileName = "sample.pdf"; // provide the path to pdf file
        PDDocument document = null;

        try
        {
            document = PDDocument.load( new File(fileName));
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document).toString();
            System.out.println( "Text in the area:" + pdfText);
        }
        finally
        {
            if( document != null )
            {
                document.close();
            }
        }
    }
}