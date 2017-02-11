package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Project;
import model.Task;

@Stateless
public class ProjectDao {
	@PersistenceContext(unitName = "wwwHomework")
	private EntityManager entityManager;
	
	public void addProject(Project project) {
		entityManager.persist(project);
	}


	public void updateProject(Project project) {
		entityManager.merge(project);
		
	}
	
	public void deleteProject(Project project) {
		entityManager.remove(project);
		
	}
	
	public void addTask(Long projectId, Task task) {
		Project project = getProjectById(projectId);
		List<Task> tasks = project.getTasks();
		task.setState("In progress");
		tasks.add(task);
		entityManager.merge(project);
		
		
	}

	public Project getProjectById(Long id) {
		TypedQuery<Project> query = entityManager.createNamedQuery("getProjectById", Project.class).setParameter("id", id);
		Project project= query.getSingleResult();
		return project;
		
	}
	
}
