package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.IngredientManager;
import managers.RecipeManager;

import entities.*;

/**
 * Servlet implementation class EditRecipe
 */
@WebServlet("/EditRecipe")
public class EditRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	RecipeManager recipeManager;
	@EJB
	IngredientManager ingredientManager;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int id = -1;
		try
		{
			id = Integer.parseInt(request.getParameter("id"));
			if (request.getParameter("change") != null)
			{
				Recipe u = new Recipe();
				u.setId(id);
				u.setDescription(request.getParameter("description"));
				u.setDirections(request.getParameter("directions"));
				u.setRecipeName(request.getParameter("name"));
				u.setDateAdded(recipeManager.getRecipe(id).getDateAdded());
				u.setIngredients(new ArrayList<Ingredient>());
				if(request.getParameterValues("recipeIngredients")!=null)
				{
					for(String s:request.getParameterValues("recipeIngredients"))
					{
						int ingId = -1;
						try
						{
							ingId = Integer.parseInt(s);
							u.getIngredients().add(ingredientManager.getIngredient(ingId));
						}
						catch (Exception e)
						{
							response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to find/modify ingredient!");
							return;
						}
					}
				}
				recipeManager.update(u);
			}
			else if (request.getParameter("delete") != null)
			{
				recipeManager.delete(id);
			}
			response.sendRedirect(getServletContext().getContextPath()+"/Home");
			return;
		} 
		catch (Exception e){}
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to find/modify recipe!");
	}

}
