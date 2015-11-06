package org.aht.test;

import org.aht.data.DataImport;
import org.aht.engine.ItemRecommender;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by azu on 30/10/15.
 */
public class Main {
    public static void main(String args[]){
        // Database info
        String username="neo4j";
        String password="n0m3l0s3";
        String categorias = "file:///home/azu/Proyectos/AHTRecommenderSystems/AHT-Docs/Data/categories.csv";
        String platillos = "file:///home/azu/Proyectos/AHTRecommenderSystems/AHT-Docs/Data/dishes.csv";
        String dishes_categories = "file:///home/azu/Proyectos/AHTRecommenderSystems/AHT-Docs/Data/dish_has_category.csv";
        try {
            // Connect
            Connection con = DataImport.connectToNeo4j(username, password);

            //Create Data
            DataImport di = new DataImport();
            //di.importCategories(categorias,con);
            //di.importDishes(platillos,con);
            //di.createRelationshipsDishesAndCategories(dishes_categories,con);
            //di.createSimilarityDishes(con);

            //Get recommendation forn an item
            String query = "ensalada"; //Replace with the name of another dish to get a recommendation
            int numberOfRecommendations = 15;

            ItemRecommender ir = new ItemRecommender();
            ResultSet is = ir.selectItem(query, con); //Look for the dish
            if(is.next()) {
                int dishID = is.getInt("ID");

                ResultSet rs = ir.getRecommendations(dishID, numberOfRecommendations, con); //Get recommendations for the dish. OR, you can replace ID to get recommendations of another dish
                File recommendations = new File("recommendations.csv");
                FileWriter fw = new FileWriter(recommendations);
                PrintWriter pw = new PrintWriter(fw);

                pw.println("Recomendaciones para " + query);
                System.out.println("Recomendaciones para " + query);
                pw.println(dishID + " " + is.getString("Name"));
                System.out.println(dishID + " " + is.getString("Name"));
                int i = 0;
                while (i < numberOfRecommendations && rs.next()) {
                    //Retrieve by column name
                    String dish = rs.getString("Neighbor");
                    int id = rs.getInt("ID");
                    pw.println(id + ", " + dish);
                    System.out.println(id + "\t" + dish);
                    i++;
                }
                fw.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
