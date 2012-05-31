package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.IngredientManager;

import entities.Ingredient;
import entities.Recipe;

/**
 * Servlet implementation class GetIngredient
 */
@WebServlet("/GetIngredient")
public class GetIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	IngredientManager im;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Ingredient r = im.getIngredient(id);
		if (r == null){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Entity not found");
		}
		request.setAttribute("ingredient", r);
		getServletContext().getRequestDispatcher("/WEB-INF/ingredientForm.jsp").forward(request, response);
		return;
	}

}
