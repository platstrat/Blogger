package servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import entities.Blogger;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register.do")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@Resource
	private UserTransaction utx;
	
    public Register() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		boolean fail = false;
		if (name == null || name.isEmpty()){
			request.setAttribute("name", true);
			fail = true;
		}
		if (password == null || password.isEmpty()){
			request.setAttribute("password", true);
			fail = true;
		}
		
		if (fail){
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		try{
			EntityManager em = emf.createEntityManager();
			Query q = em.createNamedQuery("Blogger.username", Blogger.class);
			q.setParameter("name", name);
			if (q.getResultList().size() > 0){
				request.setAttribute("duplicate", true);
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			utx.begin();
			Blogger b = new Blogger();
			b.setUsername(name);
			b.setPassword(password);
			b.setEmail(email);
			b.setRegistered(new java.util.Date());
			em.persist(b);
			em.flush();
			utx.commit();
			
			HttpSession session = request.getSession(false);
			if (session != null){
				request.logout();
				session.invalidate();
			}
			request.login(name, password);
			response.sendRedirect(response.encodeRedirectURL("UserInfo"));
			
		} catch (Exception commit){
			commit.printStackTrace();
			try{
				utx.rollback();
			} catch (Exception rollback){
				rollback.printStackTrace();
			}
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
