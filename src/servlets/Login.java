package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.ejb.EJB;

import managers.BloggerManager;

import com.sun.xml.ws.security.trust.elements.Encryption;

import entities.Blogger;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @EJB
    BloggerManager bm;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.logout();
		List<String> errors = new ArrayList<>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<Blogger> bloggers = bm.getBloggers();
		Blogger user = null;
	  	for(Blogger b : bloggers)
		{
	  		if(b.getUsername().equals(username)) user = b;
	  	}
		try
		{
			if(username == null || username.isEmpty()) {
				errors.add("Invalid username");
			}
			if(password == null || password.isEmpty()) {
				errors.add("Invalid password");
			}
			if(errors.size() > 0) {
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			request.getSession().setAttribute("name", username);
			System.out.println("Logged in");
			if(user != null)
				request.getSession().setAttribute("user", user);
			request.login(username,password);
			
	
			}
		catch(ServletException e) {
			errors.add("Invalid username/password combination");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/viewblogger.jsp").forward(
		request, response);	
	}
}
