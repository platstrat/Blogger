package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "groupByName",
			query = "select g from BloggerGroup g where g.name = :groupName")
@Table(name = "groups")
public class BloggerGroup implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "group_id",
			unique = true,
			nullable = false)
	private int groupId;
	
	@Column(name ="name", unique = true, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "groups")
	private List<Blogger> bloggers;
	
	public BloggerGroup(){}
	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public List<Blogger> getBloggers(){
		if (this.bloggers == null) this.bloggers = new Vector<>();
		return this.bloggers;
	}
	
	public void setBloggers(List<Blogger> bloggers){
		this.bloggers = bloggers;
	}
}
