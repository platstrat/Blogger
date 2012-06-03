package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
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
    
	private List<String> type;
	
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

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
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
}
