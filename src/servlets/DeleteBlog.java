package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.BlogManager;

/**
 * Servlet implementation class DeleteBlog
 */
@WebServlet("/DeleteBlog")
public class DeleteBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	BlogManager bm;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBlog() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bm.remove(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("showAllBlogs.do");
	}
}
