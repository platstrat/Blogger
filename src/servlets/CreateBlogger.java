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

import managers.BloggerManager;

import entities.Blogger;

/**
 * Servlet implementation class CreateBlogger
 */
@WebServlet("/NewBlogger")
public class CreateBlogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BloggerManager um;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBlogger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String username = request.getParameter("username");
		if(username == null || username.isEmpty()) {
			errors.add("Invalid name");
		}
		String password = request.getParameter("password");
		// Encrypt/digest password
		
		String email = request.getParameter("email");
		
		Blogger u = new Blogger();
		u.setUsername(username);
		u.setPassword(password);
		u.setEmail(email);
		u.setRegistered(new java.util.Date());
		um.create(u);
		
		// showAllBloggers.do is a mapping to a JSP file inside WEB-INF
		response.sendRedirect("showAllBloggers.do");
	}

}

