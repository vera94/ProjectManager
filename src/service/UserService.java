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
import dao.UserDao;
import model.Project;
import model.Task;
import model.User;

@Path("/user")
@Stateless
public class UserService {
	@EJB
	private UserDao userDAO;

	@GET
	public String hello() {

		//userDAO.pp();
		return "asd";
	}

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Path("/{email}/projects")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getProjectsForUser(@PathParam("email") String email) {
		List<Project> projectsForUser = new ArrayList<Project>();
		if (userDAO.existUser(email)) {
			projectsForUser = userDAO.getProjectsForUser(email);
			
		}
		return projectsForUser;
	}
	

	
	@Path("/{email}/addProject")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addProjectForUser(@PathParam("email") String email, Project project) {
		userDAO.addProject(email,project);
	}
	
	@Path("/{email}/tasks")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasksForUser(@PathParam("email") String email) {
		List<Task> tasksForUser = new ArrayList<Task>();
		if (userDAO.existUser(email)) {
			tasksForUser = userDAO.getTasksForUser(email);
		}
		return tasksForUser;
	}
	@Path("/{email}/addTask/{id}")
	@POST
	public void addTaskForUser(@PathParam("email") String email,@PathParam("id") Long id) {
		userDAO.addTask(email,id);
	}
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addUser(User user) {
		if (!userDAO.existUser(user.getEmail()))
			userDAO.addUser(user);
	}

	@Path("/update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Path("/delete")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteUser(User user) {
		userDAO.deleteUser(user);
	}
}
