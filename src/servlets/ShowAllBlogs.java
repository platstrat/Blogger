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
 * Servlet implementation class ShowAllBlogs
 */
@WebServlet("/showAllBlogs.do")
public class ShowAllBlogs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Ejb
	BlogManager blogManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("allblogs", blogManager.getBlogs());
		request.getRequestDispatcher("viewallblogs.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
}
