package cf.apps.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;
/**
 * Servlet implementation class SimpleServlet
 * 
 * Database credentials are obtained from the class HDIEnvironment which retrieves and
 * parses the environment variable VCAP_SERVICES. The contents of VCAP_SERVICES are 
 * displayed.
 * <p>
 * A sample database query is made using the class HDIQueryTests which will use the
 * HDIEnvironment class for database credentials. 
 */

@WebServlet("/hello")
public class SimpleServlet extends HttpServlet {

private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SimpleServlet() {
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

         // Set response content type
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         Statement stmt = null;
         Connection conn = null;
         String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
       
         out.println(docType +
            "<html>\n" +
            "<head><title>Cloud Foundry Database Tests</title></head>\n" +
            "<body bgcolor = \"#f0f0f0\">\n" +
            "<h1 align = \"center\">Database Connection Tests</h1>\n");

         try {
             
            HDIEnvironment ev = new HDIEnvironment();
            out.println("<h3>Environment info from VCAP_SERVICES</h3>\n");            
            out.println("<p>URL: " + ev.getUrl());
            out.println("<p>User: " + ev.getUser());
            out.println("<p>Password: " + ev.getPassword());
            out.println("<p>Port: " + ev.getPort());
            out.println("<p>VCAP_SERVICES: " + ev.getVcap_services());  

            HDIQueryTests hsc = new HDIQueryTests(response);
            out = response.getWriter();
            out.println("<h3>Query results</h3>\n");             
            out.println("<p>Query: " + hsc.getSQL());
            List<String> results = hsc.getResults();

      for (String str : results) {
      out.println("<p>Result: " + str);
      }         
            out.println("</body></html>");
           

      } 

         catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
         } finally {
            //finally block used to close resources
           try {
               if(stmt!=null)
                  stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
               if(conn!=null)
               conn.close();
            } catch(SQLException se) {
               se.printStackTrace();
            } //end finally try
         } //end try
    }
    

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
}

}


//package cf.apps.servlet;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class SimpleServlet
// */
//@WebServlet("/hello")
//public class SimpleServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public SimpleServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
