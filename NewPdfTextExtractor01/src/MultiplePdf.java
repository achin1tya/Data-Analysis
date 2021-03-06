import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.io.*;
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

    PdfToText []filenameArray;
    HashMap<String,ArrayList<DictionaryObject>> mainDictionary;
    HashMap<String,HashMap<String,Integer>> mainDictionaryUsingHashmapInside;
    //Constructor To add A preloaded file
    public MultiplePdf()
    {
        this.mainDictionary=new HashMap<>();
        this.mainDictionaryUsingHashmapInside=new HashMap<>();
        this.filenameArray=null;
    }
    //Constructor for default case
//    public void MultiplePdf (HashMap<String,HashMap<String,Integer>> mainDictionaryUsingHashmapInside)
//    {
//        this.mainDictionaryUsingHashmapInside=mainDictionaryUsingHashmapInside;
//        this.mainDictionary=null;
//    }
    //Constructor for only mainDictionary using ArrayList
    public void MultiplePdf(HashMap<String,ArrayList<DictionaryObject>> mainDictionary)
    {
        this.mainDictionary=mainDictionary;
        this.mainDictionaryUsingHashmapInside=null;
    }
    public MultiplePdf(String filename) {
        super(filename);
    }


    //making dictionary using Hashmap
    public void makeDictionaryUsingHashMap(PdfToText[] files)
    {

        for(PdfToText pdfFile:files)
        {
            String nameOfFile=pdfFile.filename;
            for (String word : pdfFile.dictionary.keySet())
            {
                int count=pdfFile.dictionary.get(word);
                if(this.mainDictionaryUsingHashmapInside.containsKey(word)) {
                    this.mainDictionaryUsingHashmapInside.get(word).put(nameOfFile, count);
                }
                else
                {
                    HashMap<String,Integer> fileDictionary;
                    fileDictionary=new HashMap<>();
                    fileDictionary.put(nameOfFile,count);
                    this.mainDictionaryUsingHashmapInside.put(word,fileDictionary);
                }
            }
        }
    }


    // Making Dictionary using  ArrayList Inside HashMap
    public void makeDictionaryOfMultiplePdf(PdfToText[] files)
    {



        int len=files.length;
        for (PdfToText pdfFile:files) {             // Going into each file
            for (String word:pdfFile.dictionary.keySet()) {             // going through each  word of file
                int count=pdfFile.dictionary.get(word);                 //getting count of current word in that file
                String nameOfCurrentFile=pdfFile.filename;              // getting name of currentfile
                DictionaryObject currentObject=new DictionaryObject(count,nameOfCurrentFile);  // creating object to be  placed in the ArrayList
                if(this.mainDictionary.containsKey(word))  // If word is already present we just append the current dictionary object to it
                {
                    this.mainDictionary.get(word).add(currentObject);
                }
                else  //When new Word is Not Found We add the word as key and its ArrayList of Count and Filename A DictionaryObjectArray
                {
                    ArrayList<DictionaryObject> DictionaryObjectArray=new ArrayList<>();
                    DictionaryObjectArray.add(currentObject);
                    this.mainDictionary.put(word,DictionaryObjectArray);
                }

            }// End of currentWord
        }//End of currentFile
    }// End of makeDictionaryOfMultiplePdf
    public void printDictionaryUsingHashMap ()throws EmptymainDictionaryUsingHashMap
    {
        if(this.mainDictionaryUsingHashmapInside==null)
            throw new EmptymainDictionaryUsingHashMap();

        for (String word:this.mainDictionaryUsingHashmapInside.keySet()) {
//            System.out.println("WORD : "+word);
            int inNoOfFiles=0;
            for (String filePath:this.mainDictionaryUsingHashmapInside.get(word).keySet())
            {
                inNoOfFiles++;
//                System.out.println(" Filename :"+ filePath+ "\n Count :"+this.mainDictionaryUsingHashmapInside.get(word).get(filePath));
            }
            if(inNoOfFiles>1)
            System.out.println("this word was in "+ inNoOfFiles+ " files ");
        }
    }
    public static void main(String[] args) throws IOException, EmptyTextFieldException, EmptyDictionaryException, EmptymainDictionaryUsingHashMap {
       String[] fileLocationStrings=new String[5000];   // max 5000 files can bes stored
       Scanner s=new Scanner(System.in);

        System.out.println("Enter number of files");
        int numberOfFiles=s.nextInt();
        s.nextLine();
        String current;
        String []StringOfFiles=new String[2000];
        for (int i = 0; i <numberOfFiles ; i++) {
            StringBuffer str=new StringBuffer(s.nextLine());
            int len=str.length();
//            System.out.println(str);
//            System.out.println(str.substring(1,len-1));
            StringOfFiles[i]=str.substring(1,len-1).toString();
//            System.out.println(StringOfFiles[i]+" len is "+len);
        }

        //System.out.println(StringOfFiles[0]);

        PdfToText[] files=new PdfToText[numberOfFiles];
        for (int i = 0; i <numberOfFiles ; i++) {
            PdfToText currentFile=new PdfToText(StringOfFiles[i]);
            files[i]=currentFile;
//            System.out.println(files[i].filename);
            files[i].getTextfromPDF();
            files[i].makeDictionary(files[i].getWords());
        }

        MultiplePdf DirectoryOfFiles=new MultiplePdf();
        DirectoryOfFiles.filenameArray=files;
        DirectoryOfFiles.makeDictionaryUsingHashMap(DirectoryOfFiles.filenameArray);
        DirectoryOfFiles.printDictionaryUsingHashMap();
       }



}

//"C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf"
//"C:\\Users\\Lenovo\\Desktop\\ImageToText\\New folder\\Three Similiar Qualification Files\\NSQF-Taxi Driver.pdf"
