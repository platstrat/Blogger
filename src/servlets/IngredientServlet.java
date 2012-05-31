package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.IngredientManager;

/**
 * Servlet implementation class RecipeServlet
 */
@WebServlet("/ingredients")
public class IngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	IngredientManager im;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("ingredients", im.getIngredients());
		request.getRequestDispatcher("/WEB-INF/allIngredients.jsp").forward(request, response);
	}

}
