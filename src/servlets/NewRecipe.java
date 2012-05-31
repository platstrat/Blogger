package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.RecipeManager;
import entities.Ingredient;
import entities.Recipe;
import entities.RecipeImage;

@WebServlet("/NewRecipe")
public class NewRecipe extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@EJB
	RecipeManager recipeManager;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String name = request.getParameter("recipeName");
		String description = request.getParameter("description");
		String directions = request.getParameter("directions");
		Date dateAdded = new Date();
		List<Ingredient> ingredients = new ArrayList<>();
		List<RecipeImage> images = new ArrayList<>();
		List<String> errors = new ArrayList<>();
		if (name == null || name.isEmpty()){
			errors.add("Invalid Recipe Name");
		}
		
		
		if (errors.size() > 0){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/allRecipes.jsp").forward(request, response);
			return;
		}
		
		Recipe r = new Recipe();
		r.setRecipeName(name);
		r.setDescription(description);
		r.setDirections(directions);
		r.setDateAdded(dateAdded);
		r.setIngredients(ingredients);
		r.setImages(images);
		recipeManager.create(r);
		response.sendRedirect(getServletContext().getContextPath()+"/Home");

	}
	
}
