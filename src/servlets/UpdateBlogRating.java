package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.BlogManager;

import entities.Blog;
import entities.Rating;

/**
 * Servlet implementation class UpdateBlogRating
 */
@WebServlet("/UpdateRating/*")
public class UpdateBlogRating extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	BlogManager bm;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String rating = request.getParameter("rating");
		Blog b = bm.getBlog(Integer.parseInt(request.getPathInfo().replace("/", "")));
		if(rating != null)
		{
			Rating r = new Rating();
			r.setBlog(b);
			r.setBlogger(b.getBlogger());
			r.setRating(Integer.parseInt(rating));
			List<Rating> ratings = b.getRatings();
			ratings.add(r);
			b.setRatings(ratings);
			bm.update(b);
		
		}

		request.setAttribute("blog", b);
		request.setAttribute("average", b.getAverage());
		request.getRequestDispatcher("/viewblog.jsp").forward(request, response);
	}
}
