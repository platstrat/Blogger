package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.BlogManager;

import entities.Blog;
import entities.Rating;

/**
 * Servlet implementation class UpdateBlog
 */
@WebServlet("/UpdateBlog")
public class UpdateBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BlogManager bm;
       
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
		int id = Integer.parseInt(request.getParameter("id"));
		Blog b = bm.getBlog(id);
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
		else if (request.getParameter("delete") != null)
		{
			bm.remove(id);
		}
		request.getSession().setAttribute("blog", b);
		request.getRequestDispatcher("viewblog.jsp").forward(request, response);
	}
}
