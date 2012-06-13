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

import managers.BlogManager;

import entities.Blog;

/**
 * Servlet implementation class CreateBlog
 */
@WebServlet("/CreateBlog")
public class CreateBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BlogManager bm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<>();
		String name = request.getParameter("name");
		if(name == null || name.isEmpty()) {
			errors.add("You must name your blog");
		}
		String type = request.getParameter("type");
		if(type == null || type.isEmpty()) {
			errors.add("Must select a blog type");
		}
		String content = request.getParameter("content");
		String tags = request.getParameter("tags");
		
		Blog b = new Blog();
		b.setName(name);
		b.setType(type);
		b.setContent(content);
		b.setCreated(new java.util.Date());
		b.setTags(tags);
		bm.create(b);
		
		request.getSession().setAttribute("blogs", bm.getBlogs());
		request.getRequestDispatcher("/WEB-INF/viewblog.jsp").forward(request, response);
	}

}
