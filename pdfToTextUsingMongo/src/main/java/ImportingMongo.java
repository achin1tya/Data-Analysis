import com.mongodb.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

public class ImportingMongo extends MultiplePdf {
    public  void importMongoAndRun() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB database = mongoClient.getDB("HelloWorld");
        DBCollection collection = database.getCollection("FirstCollection");

        System.out.println(database.getCollection("FirstCollection"));
        mongoClient.close();

    }
    public  void createPdfDocument(MultiplePdf pdf) throws UnknownHostException
    {
//        List<Integer> books = Arrays.asList(27464, 747854);
//        DBObject person = new BasicDBObject("_id", "jo")
//                .append("name", "Jo Bloggs")
//                .append("address", new BasicDBObject("street", "123 Fake St")
//                        .append("city", "Faketon")
//                        .append("state", "MA")
//                        .append("zip", 12345))
//                .append("books", books);
//            MongoClient mongoClient = new MongoClient("localhost",27017);
//            DB database = mongoClient.getDB("HelloWorld");
//            DBCollection collection = database.getCollection("FirstCollection");
//            collection.insert(person);
        HashMap<String,HashMap<String,Integer>> map=pdf.mainDictionaryUsingHashmapInside;
        HashMap<String,ArrayList<DictionaryObject>>arrayMap=pdf.mainDictionary;
        DBObject dictionary=new BasicDBObject("id","allFiles")
                .append("dictionaryUsingHashmap",map)
                .append("dictionaryUsingArrayListAndDictionaryObject",arrayMap);
        MongoClient mongoClient=new MongoClient("localhost",27017);
        DB database=mongoClient.getDB("HelloWorld");
        DBCollection collection=database.getCollection("FirstCollection");
        collection.insert(dictionary);
        mongoClient.close();
    }

    public static void main(String[] args) throws IOException, EmptyDictionaryException, EmptyTextFieldException {
        ImportingMongo m1=new ImportingMongo();
        m1.importMongoAndRun();
        MultiplePdf obj1=new MultiplePdf();
        Scanner s=new Scanner(System.in);
        obj1.getFiles(s);
        obj1.makeDictionaryUsingHashMap(obj1.filenameArray);
        //obj1.makeDictionaryOfMultiplePdf(obj1.filenameArray);
        m1.createPdfDocument(obj1);
    }
}
