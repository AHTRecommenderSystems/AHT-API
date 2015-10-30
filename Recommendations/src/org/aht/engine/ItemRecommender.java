package org.aht.engine;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by azu on 27/10/15.
 */
public class ItemRecommender {

    public ResultSet getRecommendations(String query,Connection con){
        ResultSet rs = null;
        try(Statement stmt = con.createStatement()){
            rs = stmt.executeQuery("MATCH    (d1:Dish {dishName:'"+query+"'})-[s:SAME_CATEGORIES]-(d2:Dish) WITH     d2, s.similarity AS sim ORDER BY sim DESC LIMIT 5 RETURN   d2.dishName AS Neighbor, sim AS Similarity;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet selectItem(String query, Connection con) throws SQLException {
        ResultSet rs = null;
        try(Statement stmt = con.createStatement()){
            rs = stmt.executeQuery("MATCH (d:Dish {dishName:'"+query+"'}) return d;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
