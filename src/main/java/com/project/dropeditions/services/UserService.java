package com.project.dropeditions.services;

import java.util.List;

import com.project.dropeditions.entities.AppUser;



public interface UserService {
	
	public AppUser createUser(AppUser user);
	public void deleteUser(Long id);
	public AppUser modifyUser(AppUser newUser, Long id);
	public AppUser getById(Long id);
	public List<AppUser> getAllUsers();
	public AppUser findUserByEmail(String email);

}
