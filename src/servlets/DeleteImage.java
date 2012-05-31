package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Recipe;

import managers.ImageManager;
import managers.RecipeManager;

/**
 * Servlet implementation class DeleteImage
 */
@WebServlet("/DeleteImage")
public class DeleteImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ImageManager im;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int recipeId = Integer.parseInt(request.getParameter("recipeId"));
		int id = Integer.parseInt(request.getParameter("id"));
		im.delete(id, recipeId);
		response.sendRedirect(getServletContext().getContextPath()+"/Home");
		return;
	}

}
