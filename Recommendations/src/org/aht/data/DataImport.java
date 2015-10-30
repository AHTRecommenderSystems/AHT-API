package org.aht.data;

import java.sql.*;

/**
 * Created by azu on 30/10/15.
 */
public class DataImport {
    public static Connection connectToNeo4j(String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.neo4j.jdbc.Driver");
        return DriverManager.getConnection("jdbc:neo4j://localhost:7474/", username, password);
    }
    public boolean importCategories(String file_url, Connection con) throws ClassNotFoundException, SQLException {
        // Querying
        try(Statement stmt = con.createStatement())
        {
            ResultSet rs = stmt.executeQuery("USING PERIODIC COMMIT\n" +
                    "LOAD CSV WITH HEADERS FROM \""+file_url+"\" AS row CREATE (:Category {categoryID: row.id, categoryType: row.type, categoryName: row.name});");
            ResultSet rs2 = stmt.executeQuery("CREATE INDEX ON :Category(categoryID);");
        }
        return false;
    }

    public boolean createSimilarityDishes(Connection con){
        // Querying
        try(Statement stmt = con.createStatement())
        {
            ResultSet rs = stmt.executeQuery("MATCH (d1:Dish)-[x:IS]->(c1:Category)<-[y:IS]-(d2:Dish) WITH  d1,d2,count(c1) as categories MERGE (d1)-[s:SAME_CATEGORIES]-(d2) SET s.similarity = categories;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void importDishes(String file_url, Connection con) {
        // Querying
        try(Statement stmt = con.createStatement())
        {
            ResultSet rs = stmt.executeQuery("USING PERIODIC COMMIT\n" +
                    "LOAD CSV WITH HEADERS FROM \""+file_url+"\" AS row CREATE (:Dish {dishID: toInt(row.id), dishName: row.name, ingredients: row.ingredients});");
            ResultSet rs2 = stmt.executeQuery("CREATE INDEX ON :Dish(dishID);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createRelationshipsDishesAndCategories(String dishes_categories, Connection con) {
        try(Statement stmt = con.createStatement())
        {
            ResultSet rs = stmt.executeQuery("USING PERIODIC COMMIT\n" +
                    "LOAD CSV WITH HEADERS FROM \""+dishes_categories+"\" AS row MATCH (dish:Dish {dishID: toInt(row.dish_id)}) MATCH (category:Category {categoryID: toInt(row.category_id)}) MERGE (dish)-[:IS]->(category);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
