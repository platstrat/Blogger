package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Recipe;

import managers.IngredientManager;
import managers.RecipeManager;

/**
 * Servlet implementation class GetRecipe
 */
@WebServlet("/GetRecipe")
public class GetRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	RecipeManager recipeManager;
	
	@EJB
	IngredientManager im;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Recipe r = recipeManager.getRecipe(id);
		if (r == null){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Entity not found");
		}
		request.setAttribute("recipe", r);
		request.setAttribute("ingredients",im.getIngredients());
		getServletContext().getRequestDispatcher("/WEB-INF/recipeForm.jsp").forward(request, response);
		return;
	}

}
