package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Blogger;

@Stateless
@LocalBean
public class BloggerManager {
	@PersistenceContext
	EntityManager em;
	
	public List<Blogger> getBloggers() {
		return em.createNamedQuery("Blogger.findAll", Blogger.class).getResultList();
	}
	
	public Blogger getBlogger(int id) {
		return em.find(Blogger.class, id);
	}
	
	public Blogger create(Blogger b) {
		em.persist(b);
		return b;
	}
	
	public Blogger update(Blogger b) {
		em.merge(b);
		return b;
	}
	
	public void remove(int id) {
		em.remove(getBlogger(id));
	}
}
