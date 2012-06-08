package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.BloggerManager;

/**
 * Servlet implementation class DeleteBlogger
 */
@WebServlet("/DeleteBlogger")
public class DeleteBlogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BloggerManager bm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBlogger() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bm.remove(id);
		
		request.getSession().setAttribute("user", bm.getBlogger(id));
		request.getRequestDispatcher("/WEB-INF/viewallbloggers.jsp").forward(request, response);
	}

}
