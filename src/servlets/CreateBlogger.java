package servlets;

import java.io.IOException;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashSet;
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
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import managers.BloggerManager;

import entities.Blogger;
import entities.BloggerGroup;

/**
 * Servlet implementation class CreateBlogger
 */
@WebServlet("/Register")
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();

		String username = request.getParameter("username");
		if (username == null || username.isEmpty()) {
			errors.add("Invalid name");
		}
		String password = request.getParameter("password");
		if (password == null || password.isEmpty()) {
			errors.add("Invalid password");
		}
		String email = request.getParameter("email");

		try {
			EntityManager em = emf.createEntityManager();
			Query q = em.createNamedQuery("Blogger.username", Blogger.class);
			q.setParameter("username", username);
			if (q.getResultList().size() > 0) {
				Blogger b = (Blogger) q.getResultList().get(0);
				if (b.getUsername().equalsIgnoreCase(username)) {
					request.setAttribute("duplicate", true);
					errors.add("Duplicate username");
				}
			}

			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("register.jsp").forward(request,
						response);
				return;
			}

			utx.begin();
			Blogger user = new Blogger();
			user.setUsername(username);
			user.setClearPassword(password);
			user.setEmail(email);
			user.setRegistered(new java.util.Date());
			em.persist(user);
			
//			bm.create(user);

			List<BloggerGroup> groups = em.createQuery(
					"SELECT g from BloggerGroup g WHERE g.name='Users'",
					BloggerGroup.class).getResultList();
			
			if (groups.isEmpty()) {
				BloggerGroup group = new BloggerGroup();
				group.setName("Users");
				em.persist(group);
				group.getBloggers().add(user);
				user.setGroups(groups);
			} 
			else {
				for (BloggerGroup g : groups) {
					g.getBloggers().add(user);
				}
				user.setGroups(groups);
			}

			em.flush();
			utx.commit();
			System.out.println("OK");
			//if(true)return;

			HttpSession session = request.getSession(false);
			if (session != null) {
				request.logout();
				session.invalidate();
			}

			request.getSession().setAttribute("user", user);

			request.login(username, password);
			request.getRequestDispatcher("index.jsp").forward(
					request, response);
		} catch (Exception commit) {
			commit.printStackTrace();
			try {
				if (utx.getStatus() == Status.STATUS_ACTIVE)
					utx.rollback();
			} catch (Exception rollback) {
				rollback.printStackTrace();
			}
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}