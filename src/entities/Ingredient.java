package entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;

@Entity
public class Ingredient implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false, length=255)
	private String foodName;
	
	@Column(length=255)
	private String amount;
	
	@Lob
	@Column
	private String preparation;
	
	@ManyToMany(mappedBy = "ingredients", cascade = ALL)
	private List<Recipe> recipes;
	
	public Ingredient()
	{
		// TODO Auto-generated constructor stub
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFoodName()
	{
		return foodName;
	}

	public void setFoodName(String foodName)
	{
		this.foodName = foodName;
	}

	public String getAmount()
	{
		return amount;
	}

	public void setAmount(String amount)
	{
		this.amount = amount;
	}

	public String getPreparation()
	{
		return preparation;
	}

	public void setPreparation(String preparation)
	{
		this.preparation = preparation;
	}

	public List<Recipe> getRecipes()
	{
		if(recipes==null) recipes = new Vector<Recipe>();
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes)
	{
		this.recipes = recipes;
	}
	
}
