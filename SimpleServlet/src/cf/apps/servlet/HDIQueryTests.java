package cf.apps.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class HDIQueryTests {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.sap.db.jdbc.Driver";  
    private String url ="";

    //  Database credentials
    private String user = "";
    private String password = "";
    private String sql;
    private List<String> results = new ArrayList<>();
  
    public HDIQueryTests(HttpServletResponse response) {
  
    Statement stmt = null;
    Connection conn = null;
    HDIEnvironment ev = new HDIEnvironment();

    url = ev.getUrl();
    user = ev.getUser();
    password = ev.getPassword();
 
        // Register JDBC driver
     try {

       Class.forName("com.sap.db.jdbc.Driver");

       // Open a connection
       conn = DriverManager.getConnection(url, user, password);
       // Execute SQL query
       stmt = conn.createStatement();
       ResultSet rs = null;
       sql = "SELECT NAME FROM TABLE1";
       rs = stmt.executeQuery(sql);
       // Extract data from result set
       while(rs.next()){
           results.add(rs.getString("NAME"));
       }
       // Clean-up environment
       rs.close();
       stmt.close();
       conn.close();
     } catch (ClassNotFoundException e1) {
       e1.printStackTrace();
     } catch (SQLException e) {
       e.printStackTrace();
     }    
    }

   public String getSQL() {
    return sql;
    }

   public List<String> getResults() {
    return results;
   }
}


//package cf.apps.servlet;
//
//public class HDIQueryTests {
//
//}
