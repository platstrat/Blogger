package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Blog.findAll", query="SELECT b FROM Blog b"),
	})

public class Blog 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String content;
	
    @ManyToOne(optional = false)
	private Blogger blogger;
    
    @OneToMany(mappedBy="blog")
    private List<Comment> comments;
    
    @OneToMany(mappedBy="blog")
    private List<Rating> ratings;

    @Column(updatable=false, nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date edited;
    
    
	private String type;
	
    
	private String[] tags;
	
	public Blog(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getEdited() {
		return edited;
	}

	public void setEdited(Date edited) {
		this.edited = edited;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public double getAverage() {
		double average = 0;
		for(Rating r : getRatings()) {
			average += r.getRating();
		}
		return average;
	}
}
