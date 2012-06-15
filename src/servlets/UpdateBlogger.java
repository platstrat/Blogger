package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.BloggerManager;

import entities.Blogger;

/**
 * Servlet implementation class UpdateBlogger
 */
@WebServlet("/UpdateBlogger/*")
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
		int id = Integer.parseInt(request.getPathInfo().replace("/", ""));
		String name = request.getParameter("username");
	  	String password = request.getParameter("password");
	  	String email = request.getParameter("email");
		Blogger user = bm.getBlogger(id);
		if(request.getParameter("delete") != null)
		{
			bm.remove(id);
			request.logout();
			HttpSession session = request.getSession(false);
			if(session != null) session.invalidate();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else
		{
			if(name == null || name.isEmpty()  || password == null || password.isEmpty())
			{
				String error = "Name/password cannot be empty";
				request.setAttribute("error", error);
				response.sendRedirect(request.getContextPath()+"/ViewBlogger/"+ id);
			}
			else
			{
				user.setUsername(name);
				user.setClearPassword(password);
				user.setEmail(email);
				bm.update(user);
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/ViewBlogger/"+ id);
			}
		}
		
		
		//System.out.println("User: " + user.getUsername());
		
	}

}
