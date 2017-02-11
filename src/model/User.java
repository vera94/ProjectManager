package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({ @NamedQuery(name = "findUserByMail", query = "SELECT u FROM User u WHERE u.email = :email"),
	 @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u ")})
@XmlRootElement
@Table(name = "USER")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	enum Role {
		STUDENT, TEACHER, ADMIN
	}

	@Id
	@GeneratedValue
	private long id;
	private String email;
	private String password;
	private String facultyNum;
	private String role;
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<Project> ownedProjects;
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
	        name = "USER_TASK",
	        joinColumns = {
	            @JoinColumn(name = "USER_ID",referencedColumnName="ID", nullable = true)},
	        inverseJoinColumns = {
	            @JoinColumn(name = "TASK_ID",referencedColumnName="ID",nullable = true)})
	private List<Task> tasks;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacultyNum() {
		return facultyNum;
	}

	public void setFacultyNum(String facultyNum) {
		this.facultyNum = facultyNum;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public List<Project> getOwnedProjects() {
		return ownedProjects;
	}

	public void setOwnedProjects(List<Project> projects) {
		this.ownedProjects = projects;
	}

	public User() {

	}

	public User(String email, String password, String facultyNum, String role) {
		this.email = email;
		this.password = password;
		this.facultyNum = facultyNum;
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facultyNum == null) ? 0 : facultyNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (facultyNum == null) {
			if (other.facultyNum != null)
				return false;
		} else if (!facultyNum.equals(other.facultyNum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", facultyNum=" + facultyNum
				+ ", role=" + role + "]";
	}

	public void validate() {
		// TODO Auto-generated method stub

	}

}
