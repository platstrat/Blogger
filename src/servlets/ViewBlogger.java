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
 * Servlet implementation class ViewBlogger
 */
@WebServlet("/ViewBlogger/*")
public class ViewBlogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	BloggerManager bm;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBlogger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		try {
			id = Integer.parseInt(request.getPathInfo());
		}
		catch(Exception e) {
			//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			//return;
		}
		
		if(id > -1) {
			request.setAttribute("user", bm.getBlogger(id));
			request.getRequestDispatcher("/WEB-INF/viewblogger.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}

}
