package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.BloggerManager;

import entities.Blogger;

/**
 * Servlet implementation class UpdateBlogger
 */
@WebServlet("/UpdateBlogger")
public class UpdateBlogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	BloggerManager bm;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBlogger() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Blogger b = bm.getBlogger(id);
		b.setUsername(request.getParameter("username"));
		b.setPassword(request.getParameter("password"));
		b.setEmail(request.getParameter("email"));
		bm.update(b);
		
		request.getSession().setAttribute("blogger", bm.getBlogger(id));
		request.getRequestDispatcher("/WEB-INF/viewallbloggers.jsp").forward(request, response);
	}

}
