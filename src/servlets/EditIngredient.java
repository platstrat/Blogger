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

/**
 * Servlet implementation class EditIngredient
 */
@WebServlet("/EditIngredient")
public class EditIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	IngredientManager im;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		try
		{
			id = Integer.parseInt(request.getParameter("id"));
			if (request.getParameter("change") != null)
			{
				Ingredient u = new Ingredient();
				u.setId(id);
				u.setFoodName(request.getParameter("name"));
				u.setAmount(request.getParameter("amount"));
				u.setPreparation(request.getParameter("preparation"));
				u.setRecipes(im.getIngredient(id).getRecipes());
				im.update(u);
			}
			else if (request.getParameter("delete") != null)
			{
				im.delete(id);
			}
			response.sendRedirect(getServletContext().getContextPath()+"/ingredients");
			return;
		} 
		catch (Exception e){}
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to find/modify user!");
	}

}
