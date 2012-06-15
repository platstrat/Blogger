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
import entities.Blogger;
import entities.Rating;

/**
 * Servlet implementation class ShowBlog
 */
@WebServlet("/ViewBlog/*")
public class ViewBlog extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	@EJB
	BlogManager blogManager;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Blogger user = (Blogger) request.getSession().getAttribute("user");
		
		int id = Integer.parseInt(request.getPathInfo().replace("/", ""));
		Blog b = blogManager.getBlog(id);
		System.out.println(b);
		if (b == null){
			//sresponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Entity not found");
		}
		request.setAttribute("user", user);
		request.setAttribute("blog", b);
		request.setAttribute("average", b.getAverage());
		request.getRequestDispatcher("/viewblog.jsp").forward(request, response);
	}
}
