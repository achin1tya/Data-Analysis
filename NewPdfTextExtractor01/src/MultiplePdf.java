import java.io.IOException;
import java.util.*;
import java.lang.*;
class DictionaryObject
{
    int countOfWord;
    String fileName;
    public DictionaryObject(int countOfWord,String fileName)
    {
        this.countOfWord=countOfWord;
        this.fileName=fileName;
    }

}
public class MultiplePdf extends PdfToText{

    public MultiplePdf(String filename) {
        super(filename);
    }

    public void makeDictionaryOfMultiplePdf()
    {

    }

    public static void main(String[] args) throws IOException {
       String[] fileLocationStrings=new String[5000];
       Scanner s=new Scanner(System.in);
       int numberOfFiles=s.nextInt();
       for (int i=0;i<numberOfFiles;i++)
       {
           fileLocationStrings[i]=s.nextLine();
       }
       PdfToText[] files=new PdfToText[2000];
       int index=0;
       for (String path:fileLocationStrings) {
           files[index]=new PdfToText(path);
           index++;
       }
        for (int i = 0; i <numberOfFiles ; i++) {
            files[i].getText();

        }
    }
}

