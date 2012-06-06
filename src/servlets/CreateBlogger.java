package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
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

import managers.BloggerManager;

import entities.Blogger;

/**
 * Servlet implementation class CreateBlogger
 */
@WebServlet("/NewBlogger")
public class CreateBlogger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@EJB
	BloggerManager bm;
	
	@Resource
	private UserTransaction utx;
       
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
		if(username == null || username.isEmpty()) {
			errors.add("Invalid password");
		}
		String email = request.getParameter("email");
		
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("showAllBloggers.do").forward(request, response);
			return;
		}
		
		try{
			EntityManager em = emf.createEntityManager();
			Query q = em.createNamedQuery("Blogger.username", Blogger.class);
			q.setParameter("username", username);
			if (q.getResultList().size() > 0){
				request.setAttribute("duplicate", true);
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			utx.begin();
			Blogger b = new Blogger();
			b.setUsername(username);
			b.setPassword(password);
			b.setEmail(email);
			b.setRegistered(new java.util.Date());
			bm.create(b);
			
			request.getSession().setAttribute("blogger", b);
			
			em.flush();
			utx.commit();
			
			HttpSession session = request.getSession(false);
			if (session != null){
				request.logout();
				session.invalidate();
			}
			request.login(username, password);
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
		request.getRequestDispatcher("/WEB-INF/viewblogger.jsp").forward(request, response);
	}

}

