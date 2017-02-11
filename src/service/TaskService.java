package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import dao.TaskDao;
import model.Task;

@Path("/task")
@Stateless
public class TaskService {
	@EJB
	private TaskDao taskDAO;

	@GET
	public String hello() {
		return "asd";
	}

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addTask(Task task){
		 taskDAO.addTask(task);
	}
	
	@Path("/update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateTask(Task task){
		 taskDAO.updateTask(task);
	}
	
	@Path("{id}/completed")
	@PUT
	public void completeTask(@PathParam("id") Long id){
		Task task =taskDAO.getTaskById(id);
		task.setState("Completed");
		 taskDAO.updateTask(task);
	}
	
}
