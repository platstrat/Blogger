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

/**
 * Servlet implementation class UpdateBlog
 */
@WebServlet("/UpdateBlog")
public class UpdateBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BlogManager bm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBlog() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Blog b = bm.getBlog(Integer.parseInt(request.getParameter("id")));
		b.setName(request.getParameter("name"));
		b.setType(request.getParameter("type"));
		b.setContent(request.getParameter("content"));
		b.setTags(request.getParameter("tags"));
		b.setEdited(new java.util.Date());
		bm.update(b);
		
		response.sendRedirect("showAllBlogs.do");
	}

}
