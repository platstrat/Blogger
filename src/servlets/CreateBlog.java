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
import managers.BloggerManager;

import entities.Blog;
import entities.Blogger;

/**
 * Servlet implementation class CreateBlog
 */
@WebServlet("/CreateBlog")
public class CreateBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BlogManager bm;
	@EJB
	BloggerManager blm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBlog() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException
    {
    	request.getRequestDispatcher("/WEB-INF/createblog.jsp").forward(request, response);
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
		Blogger blogger = (Blogger)request.getSession(false).getAttribute("user");
		
		b.setBlogger(blogger);
		b.setName(name);
		b.setType(type);
		b.setContent(content);
		b.setCreated(new java.util.Date());
		b.setEdited(new java.util.Date());
		b.setTags(tags.split(","));
		blogger.setPosts(blogger.getPosts() + 1);
		bm.create(b);
		List<Blog> blogs = blogger.getBlogs();
		blogs.add(b);
		blogger.setBlogs(blogs);
		blm.update(blogger);
		request.getSession().setAttribute("blog", b);
		response.sendRedirect("viewblog.jsp");
	}

}
