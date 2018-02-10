package Fabflix;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class shoppingCartServlet
 */
@WebServlet("/shoppingCartServlet")
public class shoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String movieID = request.getParameter("movieID");
		String title = request.getParameter("title");
		HttpSession session = request.getSession();
		ShoppingCart currentShoppingCart =  (ShoppingCart) session.getAttribute("shoppingCart");
		if(currentShoppingCart == null){
			currentShoppingCart = new ShoppingCart();
		}
//		if(currentShoppingCart.getIDItems().containsKey(movieID)) {
//			
//		}
		Movie m = new Movie();
		m.setMovieID(movieID);
		m.setTitle(title);
		
		currentShoppingCart.addToCart(movieID, m);
		
		session.setAttribute("shoppingCart", currentShoppingCart); 
		response.sendRedirect("shoppingCart.jsp");
		
		//forward request will call this servlet each time the web page get refreshing.
//		RequestDispatcher cart = request.getRequestDispatcher("/shoppingCart.jsp");
//		cart.forward(request, response);
//	    response.sendRedirect("shoppi"); 
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
