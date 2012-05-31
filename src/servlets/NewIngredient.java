package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Ingredient;

import managers.IngredientManager;

/**
 * Servlet implementation class NewIngredient
 */
@WebServlet("/NewIngredient")
public class NewIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	IngredientManager im;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("foodName");
		String amount = request.getParameter("amount");
		String preparation = request.getParameter("preparation");
		List<String> errors = new ArrayList<>();
		if (name == null || name.isEmpty())
		{
			errors.add("Invalid Recipe Name");
		}
		if (errors.size() > 0)
		{
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/allRecipes.jsp").forward(request, response);
			return;
		}
		Ingredient i = new Ingredient();
		i.setFoodName(name);
		i.setAmount(amount);
		i.setPreparation(preparation);
		im.create(i);
		response.sendRedirect(getServletContext().getContextPath()+"/ingredients");
	}

}
