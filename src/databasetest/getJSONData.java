package databasetest;

import java.sql.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class getJSONData {
    
    public static JSONArray getJSONData() {
            
        Connection conn = null;
        
        /* Create JSONArray to hold the collection of rows */
                
        JSONArray dbArray = new JSONArray();

        try {
            
            /* Identify the Server */
            
            String server = ("jdbc:mysql://localhost/p2_test");
            String username = "root";
            String password = "CS488";
            System.out.println("Connecting to " + server + "...");
            
            /* Load the MySQL JDBC Driver */
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            /* Open Connection */

            conn = DriverManager.getConnection(server, username, password);

            /* Test Connection */
            
            if (conn.isValid(0)) {
                
                /* Get the information from the database */
                
                String query = "SELECT * FROM people";
                Statement select = conn.createStatement();
                ResultSet resultset = select.executeQuery(query);
                ResultSetMetaData metadata = resultset.getMetaData();
                
                
                /* Loop through each record and store each value using the column key */
                
                while (resultset.next()){
                    
                    JSONObject record = new JSONObject();
                    
                    /* Go through each attribute in the record */
                    
                    for (int i = 2; i <= metadata.getColumnCount(); i++){
                        record.put(metadata.getColumnLabel(i),resultset.getString(i));
                    }
                    
                    dbArray.add(record);
                }
                
                

                /* Close Database Connection */
            
                conn.close();
            }
            
        }
        
        catch (Exception e) {
            System.err.println(e.toString());
        }
        
    return dbArray;
        
    }
   
}