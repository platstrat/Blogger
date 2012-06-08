package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try{
			request.logout();
			request.login(request.getParameter("username"), request.getParameter("password"));
			String redirect = request.getParameter("redirect");
			redirect = redirect == null ? "/" : redirect;
			response.sendRedirect(response.encodeRedirectURL(redirect));
		} catch (ServletException e){
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}
}
