package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Recipe;
import entities.RecipeImage;

@Stateless
@LocalBean
public class ImageManager
{
	@PersistenceContext
	EntityManager em;

	public List<RecipeImage> getImages()
	{
		TypedQuery<RecipeImage> getImages = em.createQuery(
				"SELECT r FROM RecipeImage r", RecipeImage.class);
		return getImages.getResultList();
	}

	public RecipeImage getImage(int id)
	{
		return em.find(RecipeImage.class, id);
	}

	public RecipeImage create(RecipeImage r)
	{
		em.persist(r);
		return r;
	}

	public RecipeImage update(RecipeImage r)
	{
		em.merge(r);
		return r;
	}

	public void delete(int id, int recipeId)
	{
		Recipe r = em.find(Recipe.class, recipeId);
		RecipeImage im = getImage(id);
		r.getImages().remove(im);
		em.remove(im);
	}
}
