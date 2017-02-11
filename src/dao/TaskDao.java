package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Project;
import model.Task;

@Stateless
public class TaskDao {
	@PersistenceContext(unitName = "wwwHomework")
	private EntityManager entityManager;
	
	
	public void addTask(Task task) {
		entityManager.persist(task);
	}


	public void updateTask(Task task) {
		entityManager.merge(task);
		
	}
	
	public void deleteTask(Task task) {
		entityManager.remove(task);
		
	}


	public Task getTaskById(Long id) {
		TypedQuery<Task> query = entityManager.createNamedQuery("findTaskById", Task.class).setParameter("id", id);
		Task task= query.getSingleResult();
		return task;
	}
}
