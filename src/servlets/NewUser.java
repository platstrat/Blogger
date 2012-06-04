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
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager um;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String name = request.getParameter("name");
		if(name == null || name.isEmpty()) {
			errors.add("Invalid name");
		}
		String password = request.getParameter("password");
		// Encrypt/digest password
		
		String email = request.getParameter("email");
		
		User u = new User();
		u.setName(name);
		u.setPassword(password);
		u.setEmail(email);
		u.setDateRegistered(new java.util.Date());
		um.create(u);
		
		// showAllUsers.do is a mapping to a JSP file inside WEB-INF
		response.sendRedirect("showAllUsers.do");
	}

}

