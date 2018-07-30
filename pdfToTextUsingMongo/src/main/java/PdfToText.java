import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Example program to verify the setup of pdfbox libraries
 */
//class dictionaryObject
//{
//    String word;
//    int count;
//    void dictionaryObject(String word,int count){
//        this.word=word;
//        this.count=count;
//    }
//    void dictionaryObject()
//    {
//        this.word=null;
//        this.count=0;
//    }
//}

public class PdfToText {

    /**
     * Print text present in the document
     */

    public String filename;
    public String text;
    public HashMap<String,Integer> dictionary;


    public PdfToText(String filename){
        this.filename=filename;
        this.text=null;
        this.dictionary=null;
    }
    public PdfToText(String filename, HashMap<String,Integer> dictionary)
    {
        this.filename=filename;
        this.dictionary=dictionary;
    }

    public PdfToText() {
        this.filename=null;
        this.text="";
        this.dictionary=null;
    }

    void printText()throws EmptyTextFieldException
    {
        if(this.text==null)
        {
            throw new EmptyTextFieldException();
        }
        System.out.println("the text in "+this.filename+"is "+this.text);
    }

    // To extract text from pdf file using pdfbox
    void getTextHelper() throws IOException
    {
        if(this.text!=null)
        {
            System.out.println("text already extracted ");

        }
        String pathName=this.filename ;// provide the path to pdf file
        PDDocument document = null;
        String pdfText="";
        try {
            document = PDDocument.load(new File(pathName));
            PDFTextStripper stripper = new PDFTextStripper();
             pdfText = stripper.getText(document).toString();
//            System.out.println("Text in the area:" + pdfText);
        } finally {
            if (document != null) {
                document.close();
            }
        }
        this.text=pdfText;

    }

    String getTextfromPdf()throws IOException{
        getTextHelper();
        return this.text;
    }

    String[] getWords() throws EmptyTextFieldException
    {
        if(this.text==null){
            throw new EmptyTextFieldException();
        }
        return this.text.split(" ");
    }
    //To make a dictionary of words and their count in
    void  makeDictionary(String[] listOfWords) throws EmptyDictionaryException,EmptyTextFieldException
    {
        if(this.text==null)
            throw new EmptyTextFieldException();
        List<String> uniqueWords = new ArrayList<String>();

        //words = str1.split("[!-~]* ");
        if (this.dictionary!=null)
        {
            System.out.println("the Dictionary is not empty");
        }

        for (int i = 0; i < listOfWords.length; i++)
        {
            if (!(uniqueWords.contains (listOfWords[i]))) {
                    if(listOfWords[i].contains("."))
                        continue;   // TO store hashmap in mongodb
                    else
                        uniqueWords.add(listOfWords[i]);
            }
        }
//        for (String word:uniqueWords) {
//            System.out.println(" &&&&&" +word);
//        }
        int[] countOfWords=new int[uniqueWords.size()];
        for(int i=0;i<uniqueWords.size();i++)
        {
            String uniqueWord=uniqueWords.get(i);
            int count=0;
            for(int j=0;j<listOfWords.length;j++)
            {
                if(uniqueWord.equals(listOfWords[j]))
                {
                    count++;
                }
            }
            countOfWords[i]=count;
        }
        HashMap<String,Integer> tempDictionary=new HashMap<String,Integer>();
        for (int i=0;i<uniqueWords.size();i++)
        {
            tempDictionary.put(uniqueWords.get(i),countOfWords[i]);
        }
        this.dictionary=tempDictionary;


    }

    void printDictionary() throws EmptyDictionaryException
    {
        int len=this.dictionary.size();
        if(len==0)
            throw new EmptyDictionaryException();

        for(String key : this.dictionary.keySet() ){
            System.out.println("For Key "+key+"the count is "+this.dictionary.get(key));
        }

    }

    public static float comparePdf(PdfToText obj1, PdfToText obj2) throws EmptyDictionaryException
    {
        if(obj1.dictionary==null||obj2.dictionary==null)
            throw new EmptyDictionaryException();
        HashMap<String,Integer> dict1=obj1.dictionary;
        HashMap<String,Integer> dict2=obj2.dictionary;

        float sum=0;
        int flag=0;
        for(String key :dict1.keySet())
        {
            float count=0;
            if(dict2.containsKey(key))
            {
                if(dict1.get(key).equals(dict2.get(key))) {
                    count = 1;
                }
                else
                    count=(float)Math.min(dict1.get(key),dict2.get(key))/Math.max(dict1.get(key),dict2.get(key));
            }
            sum=sum+count;
        }
        sum=(sum/Math.max(dict1.size(),dict2.size()))*100;
        return sum;
    }
//    public static void main(String[] args) throws IOException, EmptyTextFieldException, EmptyDictionaryException, EmptymainDictionaryUsingHashMap {
////        PdfToText obj1=new PdfToText("C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf");
////        PdfToText obj2=new PdfToText("C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf");
////        obj1.getText();
////        //obj1.printText();
////        obj2.getText();
////       // obj2.printText();
////        obj1.makeDictionary(obj1.getWords());
////        obj2.makeDictionary(obj2.getWords());
////        //obj1.printDictionary();
////
////        //obj2.printDictionary();
////        float ans=PdfToText.comparePdf(obj1,obj2);
//////        System.out.println("The files are "+ans+"% same");
//////        obj1.printDictionary();
////
//////        PdfToText obj1=new PdfToText("C:\\\\Users\\\\Lenovo\\\\Desktop\\\\ImageToText\\\\New folder\\\\Three different qualification files\\\\NSQF- Advance Pattern Maker (CAD-CAM).pdf" );
//////        String text=obj1.getText();
//////        System.out.println(text);
////            PdfToText obj1=new PdfToText();
////            PdfToText obj2=new PdfToText();
////            obj1.filename="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf";
////            obj2.filename="C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf";
////            obj1.getText();
////            obj2.getText();
////            obj1.makeDictionary(obj1.getWords());
////            obj2.makeDictionary(obj2.getWords());
////            float ans=comparePdf(obj1,obj2);
////        System.out.println("ans is"+ans);
////            Scanner s=new Scanner(System.in);
////            String filePath=s.nextLine();
////            PdfToText obj1=new PdfToText(filePath);
////        System.out.println(obj1.filename);
////            obj1.getText();
////            obj1.printText();
//
//     }



}