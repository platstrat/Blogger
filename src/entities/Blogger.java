package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Blogger.findAll", query="SELECT b FROM Blogger b"),
	@NamedQuery(name = "Blogger.username", query = "SELECT b from Blogger b WHERE b.username=:username")
	}) 

public class Blogger 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false, length=255)
	private String username;
	
	@Column(nullable=false, length=255)
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false, nullable=false)
	private Date registered;
	
	@Column(nullable=false)
	private int posts;
	
	@OneToMany(mappedBy="blogger")
	private List<Blog> blogs;
	
	@OneToMany(mappedBy="blogger")
	private List<Comment> comments;
	
	@OneToMany(mappedBy="blogger")
	private List<Rating> ratings;
	
	private String email;
	
	public Blogger(){} 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
}
