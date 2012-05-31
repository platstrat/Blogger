package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import managers.RecipeManager;

import entities.Recipe;
import entities.RecipeImage;

/**
 * Servlet implementation class AddImage
 */
@WebServlet("/AddImage")
public class AddImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	RecipeManager recipeManager;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part uploaded = request.getPart("fileName");
		String type = uploaded.getContentType();
		if(type==null || !type.startsWith("image"))
		{
			response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}
		InputStream clientFile = uploaded.getInputStream();
		byte[] buffer = new byte[2048*100];
		clientFile.read(buffer);
		clientFile.close();
		RecipeImage image = new RecipeImage();
		image.setContentType(type);
		image.setImage(buffer);
		int id = -1;
		try
		{
			id = Integer.parseInt(request.getParameter("id"));
			
				Recipe u = new Recipe();
				u.setId(id);
				u.setDescription(request.getParameter("description"));
				u.setDirections(request.getParameter("directions"));
				u.setRecipeName(request.getParameter("name"));
				u.setDateAdded(recipeManager.getRecipe(id).getDateAdded());
				List<RecipeImage> images = recipeManager.getRecipe(id).getImages();
				images.add(image);
				u.setImages(images);
				recipeManager.update(u);
			
			response.sendRedirect(getServletContext().getContextPath()+"/Home");
			return;
		}
		catch(Exception e){}
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to add image!");
	}

}
