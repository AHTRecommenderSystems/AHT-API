package org.aht.engine;

import java.sql.*;

/**
 * Created by azu on 27/10/15.
 */
public class ItemRecommender {

    public ResultSet getRecommendations(int dishID,Connection con){
        ResultSet rs = null;
        try(Statement stmt = con.createStatement()){
            rs = stmt.executeQuery("MATCH    (d1:Dish {dishID:"+dishID+"})-[s:SAME_CATEGORIES]-(d2:Dish) WITH     d2, s.similarity AS sim ORDER BY sim DESC LIMIT 15 RETURN   d2.dishID AS ID, d2.dishName AS Neighbor, sim AS Similarity;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet selectItem(String query, Connection con) throws SQLException {
        ResultSet rs = null;
        try(Statement stmt = con.createStatement()){
            rs = stmt.executeQuery("MATCH (d:Dish {dishName:'"+query+"'}) return d.dishID AS ID,d.dishName AS Name;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
