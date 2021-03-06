package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Blogger;

/**
 * Servlet Filter implementation class BlogFilter
 */
@WebFilter(urlPatterns={"/CreateBlog","/DeleteBlog","/ViewBlogger/*","/Logout","/Blogs","/showAllBlogs","/UpdateBlog","/UpdateBlogger"})
public class AuthorizationFilter implements Filter {

    /**
     * 
     * 
     * Default constructor. 
     */
    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		Blogger blogger = (Blogger)req.getSession(false).getAttribute("user");
		if(blogger==null||blogger.getGroups()==null||blogger.getGroups().isEmpty())
		{
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getServletContext().getContextPath()+"/register.jsp");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
