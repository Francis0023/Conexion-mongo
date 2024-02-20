package org.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import javax.swing.*;
import static sun.text.normalizer.UTF16.append;
public class Main {
    public static void main(String[] args) {
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//        MongoDatabase database = mongoClient.getDatabase("mydb2");
//        MongoCollection<Document> collection = database.getCollection("mycollection");
//
//        Document document = new Document("name","Frank")
//                .append("age",22)
//                .append("city", "New York")
//                .append("country","Ecuador");
//
//        collection.insertOne(document);
//        System.out.println("Documento insertado");

        JFrame pantalla = new JFrame("Conexion con MongoDB");
        pantalla.setContentPane(new Ingreso().ventana);
        pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantalla.pack();
        pantalla.setSize(650,550);
        pantalla.setVisible(true);
    }
}