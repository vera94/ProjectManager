package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ProjectDao;
import dao.TaskDao;
import model.Project;
import model.Task;
import model.User;

@Path("/project")
@Stateless
public class ProjectService {
	@EJB
	private ProjectDao projectDAO;

	@GET
	public String hello() {
		return "asd";
	}

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProject(Project project){
		 projectDAO.addProject(project);
	}
	
	
	
	@Path("/update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProject(Project project){
		 projectDAO.updateProject(project);
	}
	
	@Path("/delete/{id}")
	@DELETE
	public void deleteProject(@PathParam("id") Long id){
		Project project = projectDAO.getProjectById(id);	
		 projectDAO.deleteProject(project);
	}
	
	@Path("/{id}")
	@GET
	public String getProjectName(@PathParam("id") Long id) {
		Project project = projectDAO.getProjectById(id);		
		return project.getTopic();
	}
	
	@Path("/{id}/tasks")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasksForUser(@PathParam("id") Long id) {
		Project project = projectDAO.getProjectById(id);
		List<Task> tasks = project.getTasks();
		return tasks;
	}
	
	@Path("/{id}/addTask")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addTaskForProject(@PathParam("id") Long id, Task task) {
		projectDAO.addTask(id, task);
		
	}
	
	@Path("{id}/task/{taskId}")
	@DELETE
	public void deleteTask(@PathParam("id") Long id, @PathParam("taskId") Long taskId){
		Project project=projectDAO.getProjectById(id);
		List<Task> tasks = project.getTasks();
		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);
			if(task.getId().equals(taskId)){
				tasks.remove(task);
			}
		}
		projectDAO.updateProject(project);
	}

}
