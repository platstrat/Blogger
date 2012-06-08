package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Blog;
import entities.Rating;

@Stateless
@LocalBean
public class BlogManager {
	@PersistenceContext
	EntityManager em;
	
	public List<Blog> getBlogs() {
		return em.createNamedQuery("Blog.findAll", Blog.class).getResultList();
	}
	
	public Blog getBlog(int id) {
		return em.find(Blog.class, id);
	}
	
	public Blog create(Blog b) {
		em.persist(b);
		return b;
	}
	
	public Blog update(Blog b) {
		em.merge(b);
		return b;
	}
	
	public void remove(int id) {
		em.remove(getBlog(id));
	}
	public int getAverage(Blog b)
	{
		int count = 1, average = 0;
		for(Rating r : b.getRatings())
		{
			average += r.getRating();
			count++;
		}
		if(count > 1) count--;
		
		return average / count;
	}
}
