package Fabflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
        PrintWriter out = response.getWriter();

		// Verify CAPTCHA.
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
		if (!valid) {
		    //errorString = "Captcha invalid!";
		    out.println("<HTML>" +
				"<HEAD><TITLE>" +
				"MovieDB: Error" +
				"</TITLE></HEAD>\n<BODY>" +
				"<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
		    return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// this example only allows username/password to be test/test
		// in the real project, you should talk to the database to verify username/password
		String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false";
        
        
        try {
        		Class.forName("com.mysql.jdbc.Driver").newInstance();
        		Connection dbcon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
            Statement statement = dbcon.createStatement();
            
            String query = "SELECT * from customers where email = '" + username +"' AND password = '" + password + "'";
        		ResultSet rs = statement.executeQuery(query);
        		
        		
        		if(username.equals(null) || password.equals(null)) {
        			request.getSession().setAttribute("user", new User(username));
	        		JsonObject responseJsonObject = new JsonObject();
	        		responseJsonObject.addProperty("status", "fail");
	        		responseJsonObject.addProperty("message", "Please enter your email adress and password, i.e. a@email.com; a2");
//	        		if (!username.equals(uname)) {
//	        			responseJsonObject.addProperty("message", "user " + username + " doesn't exist");
//	        		} else if (!password.equals(pwd)) {
//	        			responseJsonObject.addProperty("message", "incorrect password");
//	        		}
	        		response.getWriter().write(responseJsonObject.toString());
        		} else if (!rs.next()){
        			request.getSession().setAttribute("user", new User(username));
        			JsonObject responseJsonObject = new JsonObject();
        			responseJsonObject.addProperty("status", "fail");
        			responseJsonObject.addProperty("message", "Your account can not be found, please enter again. i.e. a@email.com; a2");
        			response.getWriter().write(responseJsonObject.toString());
        		}
        		else {
        			// login success:
//        			String uname = rs.getString("email");
//            		String pwd = rs.getString("password");
        			// set this user into the session
        			request.getSession().setAttribute("user", new User(username));
        			
        			JsonObject responseJsonObject = new JsonObject();
        			responseJsonObject.addProperty("status", "success");
        			responseJsonObject.addProperty("message", "success");
        			
        			response.getWriter().write(responseJsonObject.toString());
        		}

       
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println("SQL Exception:  " + ex.getMessage());
                ex = ex.getNextException();
            } // end while
        } // end catch SQLException

        catch (java.lang.Exception ex) {
            out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>"
                    + "<P>SQL error in doGet: " + ex.getMessage() + "</P></BODY></HTML>");
            return;
        }
		
		// login fail
		
		
		

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
