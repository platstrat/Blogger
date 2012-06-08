package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Blog;
import entities.Comment;
import entities.Rating;

import managers.BlogManager;

/**
 * Servlet implementation class ViewComment
 */
@WebServlet("/UpdateComment")
public class UpdateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	BlogManager bm;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Blog b = bm.getBlog(Integer.parseInt(request.getParameter("id")));
		Comment c = new Comment();
		int commentId = Integer.parseInt(request.getParameter("cid"));
		for(Comment com : b.getComments())
		{
			if(com.getId() == commentId) c = com;
		}
		c.setContent(request.getParameter("content"));
		bm.update(b);
		
		request.setAttribute("blog", b);
		request.setAttribute("average", getAverage(b));
		request.getRequestDispatcher("/WEB-INF/viewblog.jsp").forward(request, response);
	}
	private int getAverage(Blog b)
	{
		int count = 1, average = 0;
		for(Rating r : b.getRatings())
		{
			average += r.getRating();
			count++;
		}
		if(count > 1) count--;
		
		return average / count;
	}
}
