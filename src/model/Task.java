package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@NamedQueries({ @NamedQuery(name = "findTaskById", query = "SELECT t FROM Task t WHERE t.id = :id") })
@XmlRootElement
@Table(name = "TASK")
public class Task implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Task(){
		
	}
	public Task(String name, String description, String state, String note, List<User> owners) {
		super();
		this.name = name;
		this.description = description;
		this.state = state;
		this.note = note;
		this.owners = owners;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String state;
	private String note;

	@ManyToMany(mappedBy="tasks")
	private  List<User> owners;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	public List<User> getOwners() {
		return owners;
	}
	public void setOwners(List<User> owners) {
		this.owners = owners;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	
}
