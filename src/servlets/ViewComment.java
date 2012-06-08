package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.BlogManager;

import org.jboss.weld.context.ejb.Ejb;

import entities.Blog;

/**
 * Servlet implementation class ViewComment
 */
@WebServlet("/ViewComment")
public class ViewComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Ejb
	BlogManager blogManager;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Blog b = blogManager.getBlog(id);
		if (b == null){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Entity not found");
		}
		request.setAttribute("blog", b);
		request.getRequestDispatcher("/WEB-INF/editcomment.jsp").forward(request, response);	
	}

}
