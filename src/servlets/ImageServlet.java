package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.RecipeImage;

import managers.ImageManager;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/images")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ImageManager im;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id  = Integer.parseInt(request.getParameter("id"));
		RecipeImage image = im.getImage(id);
		if(image==null)System.out.println("oops");
		response.setContentType(image.getContentType()); 
		response.getOutputStream().write(image.getImage());
		//response.sendRedirect("index.jsp");
	}

}
