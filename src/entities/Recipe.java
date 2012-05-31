package entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;



@Entity
public class Recipe implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false, length=255)
	private String recipeName;
	
	@Lob
	@Column
	private String description;
	
	@Lob
	@Column
	private String directions;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false, nullable=false)
	private Date dateAdded;
	
	@ManyToMany(cascade = ALL)
	@JoinTable(name = "ingredient_list")
	private List<Ingredient> ingredients;
	
	@OneToMany(cascade = ALL)
	@JoinTable(name = "image_list")
	private List<RecipeImage> images;
	
	private static final long serialVersionUID = 1L;
	
	public Recipe()
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

	public String getRecipeName()
	{
		return recipeName;
	}

	public void setRecipeName(String recipeName)
	{
		this.recipeName = recipeName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDirections()
	{
		return directions;
	}

	public void setDirections(String directions)
	{
		this.directions = directions;
	}

	public Date getDateAdded()
	{
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	public List<Ingredient> getIngredients()
	{
		if(ingredients==null) ingredients = new Vector<Ingredient>();
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients)
	{
		this.ingredients = ingredients;
	}

	public List<RecipeImage> getImages()
	{
		return images;
	}

	public void setImages(List<RecipeImage> images)
	{
		this.images = images;
	}
	
}
