package testPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class hello
 */
@WebServlet("/hello")
public class hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		  Context ctx;
		  try {
              System.out.println("Trying to connect");
              ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/wwwHomework");
              Connection con = ds.getConnection();
              String queryString = "SELECT * from USER";
              PreparedStatement ps =null;
              
              try {
            	  ps = con.prepareStatement(queryString);
            	  ResultSet query_results = ps.executeQuery();
            	  while (query_results.next())
            	    {
            	        
            	        System.out.println(query_results.getString("email"));
            	    }
            	  System.out.println("query" + ps.executeQuery());
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("error");
			}
              
              
              System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                      + con.getMetaData().getDatabaseProductName());
          } catch (Exception e) {
System.out.println("Unable to make connection with DB");
              e.printStackTrace();
          }
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
