package servlets;

import java.io.IOException;

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
@WebServlet("/UpdateRating")
public class UpdateBlogRating extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	BlogManager bm;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String rating = request.getParameter("rating");
		Blog b = bm.getBlog(Integer.parseInt(request.getParameter("id")));
		if(rating != null)
		{
			Rating r = new Rating();
			r.setBlog(b);
			r.setBlogger(b.getBlogger());
			r.setRating(Integer.parseInt(rating));
			b.getRatings().add(r);			
		}

		request.setAttribute("blog", b);
//		request.setAttribute("average", bm.getAverage(b));
		request.getRequestDispatcher("/WEB-INF/viewblog.jsp").forward(request, response);
	}
}
