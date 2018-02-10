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
 * Servlet implementation class UpdateShoppingCart
 */
@WebServlet("/UpdateShoppingCart")
public class UpdateShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoppingCart() {
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
		Integer quantity = null;
		String q = request.getParameter("currentQuantity");
//		System.out.println(title);
//		System.out.println(quantity);
		if(q == null){
			q ="";
		}
		if(!q.equals("")){
			quantity = Integer.parseInt(q);
		}		
		
		HttpSession session = request.getSession();		
		ShoppingCart currentShoppingCart =  (ShoppingCart) session.getAttribute("shoppingCart"); 
		
		
		if(currentShoppingCart != null && quantity != null){
			currentShoppingCart.changeQuantity(movieID, quantity);		
		}
		
		
		RequestDispatcher cart = request.getRequestDispatcher("/shoppingCart.jsp");
		cart.forward(request, response);
		session.setAttribute("shoppingCart", currentShoppingCart); 
//		response.sendRedirect("shoppingCart.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
