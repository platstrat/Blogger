package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.RecipeManager;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet({ "/Home", "/index" })
public class HomeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	RecipeManager recipeManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("recipes", recipeManager.getRecipes());
		getServletContext().getRequestDispatcher("/WEB-INF/allRecipes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
