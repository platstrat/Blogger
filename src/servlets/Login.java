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

import com.sun.xml.ws.security.trust.elements.Encryption;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.logout();
		List<String> errors = new ArrayList<>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
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
			request.login(username, password);
			
		}
		catch(ServletException e) {
			errors.add("Invalid username/password combination");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("");
	}
}
