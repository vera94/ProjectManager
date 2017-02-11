package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Project;
import model.Task;
import model.User;

@Stateless
public class UserDao {
	@PersistenceContext(unitName = "wwwHomework")
	private EntityManager entityManager;

	public User getUser(String email){
		User user =  entityManager.createNamedQuery("findUserByMail", User.class).setParameter("email", email).getSingleResult();		
		return user;
	}
	
	public void pp(){
		User user = new User("abc","abc", "123","student");
		ArrayList<Project> ownedProjects= new ArrayList<Project>();
		Project proj = new Project("as","as");
		ownedProjects.add(proj);
		user.setOwnedProjects(ownedProjects);
		entityManager.persist(user);
	}
	public void addUser(User user) {
		user.validate();
		entityManager.persist(user);
	}

	public boolean existUser(String email) {
		User user= null;
		try{
			user= getUser(email);
		}catch(NoResultException  e){
			System.out.println("Could not find user with email : " + email);
			return false;
		}catch(Exception  e){
			System.out.println("Error while trying to find user with email: " + email);
			return false;
		}
		return (user != null);
	}

	public void updateUser(User user) {
		entityManager.merge(user);
		
	}
	
	public void deleteUser(User user) {
		entityManager.remove(user);
		
	}

	public List<Project> getProjectsForUser(String email) {
		User user = getUser(email);
		List<Project> ownedProjects = user.getOwnedProjects();
		ownedProjects.size();
		return ownedProjects;
		
	}
	
	public List<Task> getTasksForUser(String email) {
		User user = getUser(email);
		List<Task> tasks = user.getTasks();
		tasks.size();
		return tasks;
		
	}

	public List<User> getAllUsers() {
		TypedQuery<User> namedQuery = entityManager.createNamedQuery("getAllUsers", User.class);
		List<User> users =  namedQuery.getResultList();		
		return users;
	}

	public void addProject(String email, Project project) {
		User user = getUser(email);
		List<Project> ownedProjects = user.getOwnedProjects();		
		ownedProjects.add(project);
		entityManager.merge(user);
		
	}

	public void addTask(String email, Long id) {
		User user = getUser(email);
		TypedQuery<Task> query = entityManager.createNamedQuery("findTaskById", Task.class).setParameter("id", id);
		Task task= query.getSingleResult();
		List<Task> tasks = user.getTasks();
		tasks.add(task);
		entityManager.merge(user);
		
		
	}

}
