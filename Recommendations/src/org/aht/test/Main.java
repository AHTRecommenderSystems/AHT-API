package org.aht.test;

import org.aht.data.DataImport;
import org.aht.engine.ItemRecommender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by azu on 30/10/15.
 */
public class Main {
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        // Database info
        String username="neo4j";
        String password="n0m3l0s3";
        String categorias = "file:///home/azu/Proyectos/AHTRecommenderSystems/AHT-Docs/Data/categories.csv";
        String platillos = "file:///home/azu/Proyectos/AHTRecommenderSystems/AHT-Docs/Data/dishes.csv";
        String dishes_categories = "file:///home/azu/Proyectos/AHTRecommenderSystems/AHT-Docs/Data/dish_has_category.csv";
        // Connect
        Connection con = DataImport.connectToNeo4j(username,password);

        //Create Data
        DataImport di = new DataImport();
        //di.importCategories(categorias,con);
        //di.importDishes(platillos,con);
        //di.createRelationshipsDishesAndCategories(dishes_categories,con);
        //di.createSimilarityDishes(con);

        //Get recommendation forn an item
        ItemRecommender ir = new ItemRecommender();
        String query = "Merengues argentinos"; //Replace with the name of another dish to get a recommendation
        ResultSet rs = ir.getRecommendations(query,con);
        System.out.println("Recomendaciones para "+query);
        while(rs.next()) {
            //Retrieve by column name
            String dish = rs.getString("Neighbor");
            System.out.println(dish);

        }

    }
}
