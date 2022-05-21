package com.FJerseyProject.webapp;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserResource {
	UserRepository repo = new UserRepository();
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		
		return repo.getUsers();
	}
	
	@GET
	@Path("user/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") int id) {
		
		return repo.getUserById(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user")
	public User createUser(User u) {
		repo.createUser(u);
		return u;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user")
	public User updateUser(User u) {
		if(repo.getUserById(u.getId())==null) {
			repo.createUser(u);
		}
		else {
		repo.updateUser(u);
		}
		return u;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user/{id}")
	public User deleteUser(@PathParam("id") int id) {
		User u = repo.getUserById(id);
		if(u.getId()!=0)
			repo.deleteUser(id);
		return u;
	}
}
