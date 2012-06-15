package servlets;

import java.io.IOException;
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
import entities.Rating;

/**
 * Servlet implementation class UpdateBlog
 */
@WebServlet("/UpdateBlog/*")
public class UpdateBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BlogManager bm;
      @EJB
      BloggerManager um;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBlog() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String tags = request.getParameter("tags");
		Blogger user = (Blogger) request.getSession().getAttribute("user"); 
		Blog b = bm.getBlog(Integer.parseInt(request.getPathInfo().replace("/", "")));
		if(name == null || name.isEmpty() ||content == null || content.isEmpty())
		{
			String error = "Name/content cannot be empty!";
			request.setAttribute("error", error);
		}
		if (request.getParameter("change") != null && name != null && !name.isEmpty() && content != null && !content.isEmpty())
		{
			b.setName(name);
			b.setContent(content);
			if(!type.isEmpty() || type != null)
				b.setType(type);
			if(!tags.isEmpty() || tags != null)
				b.setTags(tags.split(","));
			b.setEdited(new java.util.Date());
			bm.update(b);
		}
		if (request.getParameter("delete") != null)
		{
			bm.getBlogs().remove(b);
			bm.remove(b.getId());
			List<Blog> blogs = user.getBlogs();
			blogs.remove(b);
			user.setBlogs(blogs);
			um.update(user);
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/ViewBlogger/"+ user.getBloggerId());
		  	return;

		}
		request.getSession().setAttribute("blog", b);
		response.sendRedirect(request.getContextPath()+"/ViewBlog/"+ b.getId());
	}
}
