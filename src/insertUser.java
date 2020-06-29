import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Import Database Connection Class file 
import tools.DB;

// Servlet Name 
@WebServlet("/insertUser")
public class insertUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        try {

            // Initialize the database 
            Connection con = DB.initializeDatabase();

            // Create a SQL query to insert data into demo table 
            // demo table consists of two columns, so two '?' is used 
            PreparedStatement st = con.prepareStatement("insert into users (login, password) values (?, ?)");

            // For the first parameter, 
            // get the data using request object 
            // sets the data to st pointer
            String login = request.getParameter("login");
            System.out.println("login: "+ login);
            st.setString(1,login);

            // Same for second parameter 
            String password = request.getParameter("password");
            System.out.println("password: "+ password);
            st.setString(2,password);
            // Execute the insert command using executeUpdate() 
            // to make changes in database

            st.executeUpdate();

            // Close all the connections 
            st.close();
            con.close();

            // Get a writer pointer  
            // to display the successful result
            out.println("<html><body><b>Successfully Inserted"
                    + "</b></body></html>");
        }
        catch (Exception e) {
            out.println("Nie udalo sie dodac uzytkownika");
            e.printStackTrace();
        }
    }
} 