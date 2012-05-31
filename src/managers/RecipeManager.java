package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Recipe;

@Stateless
@LocalBean
public class RecipeManager
{
	@PersistenceContext
	EntityManager em;
	
	public List<Recipe> getRecipes(){
		TypedQuery<Recipe> getRecipes = em.createQuery("SELECT r FROM Recipe r", Recipe.class);
		return getRecipes.getResultList();
	}
	
	public Recipe getRecipe(int id){
		return em.find(Recipe.class, id);
	}
	
	public Recipe create(Recipe r){
		em.persist(r);
		return r;
	}
	
	public Recipe update(Recipe r){
		em.merge(r);
		return r;
	}
	
	public void delete(int id){
		em.remove(getRecipe(id));
	}
}
