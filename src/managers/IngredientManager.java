package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Ingredient;
import entities.Recipe;
import entities.RecipeImage;

@Stateless
@LocalBean
public class IngredientManager
{
	@PersistenceContext
	EntityManager em;
	
	public List<Ingredient> getIngredients(){
		TypedQuery<Ingredient> getIngredients = em.createQuery("SELECT r FROM Ingredient r", Ingredient.class);
		return getIngredients.getResultList();
	}
	
	public Ingredient getIngredient(int id){
		return em.find(Ingredient.class, id);
	}
	
	public Ingredient create(Ingredient r){
		em.persist(r);
		return r;
	}
	
	public Ingredient update(Ingredient r){
		em.merge(r);
		return r;
	}
	
	public void delete(int id){
		List<Recipe> recipes = getIngredient(id).getRecipes();
		for(Recipe recipe:recipes)
		{
			Recipe r = em.find(Recipe.class, recipe.getId());
			Ingredient in = getIngredient(id);
			r.getImages().remove(in);
		}
		em.remove(getIngredient(id));
	}
}
