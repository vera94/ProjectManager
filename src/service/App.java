package service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/")
public class App extends Application {
	
	
	@Override
	public Set<Object> getSingletons() {
		HashSet<Object> singletons = new HashSet<Object>();
		singletons.add(new UserService());
		singletons.add(new TaskService());
		singletons.add(new ProjectService());
		return singletons;
	}

}