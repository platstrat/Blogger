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

import entities.Blog;
import entities.Blogger;
import entities.Comment;

import managers.BlogManager;

/**
 * Servlet implementation class CreateComment
 */
@WebServlet("/CreateComment")
public class CreateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BlogManager bm;   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		int id = Integer.parseInt(request.getParameter("id"));
		String content = request.getParameter("content");
		Blogger user = (Blogger) request.getAttribute("user");		
		if(content == null || content.isEmpty())
		{
			errors.add("Please enter content for your blog");
		}
		Comment c = new Comment();
		Blog b = bm.getBlog(id);
		c.setBlogger(user);
		c.setBlog(b);
		c.setContent(content);
		b.getComments().add(c);
		
		request.setAttribute("blog", b);
		request.setAttribute("average", b.getAverage());
		response.sendRedirect("viewblog.jsp");
	}

}
