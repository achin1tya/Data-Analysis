import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class ImportingMongo {
    public void importMongoAndRun() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost",27017);
        DB database = mongoClient.getDB("HelloWorld");
        DBCollection collection = database.getCollection("FirstCollection");

        System.out.println(database.getCollection("FirstCollection"));
        mongoClient.close();
    }
    public void createDocument() throws UnknownHostException
    {
        List<Integer> books = Arrays.asList(27464, 747854);
        DBObject person = new BasicDBObject("_id", "jo")
                .append("name", "Jo Bloggs")
                .append("address", new BasicDBObject("street", "123 Fake St")
                        .append("city", "Faketon")
                        .append("state", "MA")
                        .append("zip", 12345))
                .append("books", books);
            MongoClient mongoClient = new MongoClient("localhost",27017);
            DB database = mongoClient.getDB("HelloWorld");
            DBCollection collection = database.getCollection("FirstCollection");


            collection.insert(person);
    }

    public static void main(String[] args) throws UnknownHostException {
        ImportingMongo m1=new ImportingMongo();
        m1.importMongoAndRun();
        m1.createDocument();
    }
}
