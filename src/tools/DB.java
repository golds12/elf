package tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;


// This class can be used to initialize the database connection
public class DB {
    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        // Database name to access
        String dbName = "elfv2";
        String dbUsername = "elf";
        String dbPassword = "elf";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);

        return con;
    }

    public static JSONArray rs2json(ResultSet rs) throws SQLException {
        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));
            }
            json.add(obj);
        }
        return json;
    }

    public static JSONArray getSqlToJSON(String sql) {
        JSONArray results = null;
        try {
            Connection con = DB.initializeDatabase();
            // Execute SQL query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            results = DB.rs2json(rs);
            // Clean-up environment
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            return results;
        }
    }
}