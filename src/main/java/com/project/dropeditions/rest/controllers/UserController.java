package com.project.dropeditions.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dropeditions.entities.AppUser;
import com.project.dropeditions.entities.RoleEnum;
import com.project.dropeditions.entities.UserForm;
import com.project.dropeditions.services.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		
        System.out.println("email " + userForm.getEmail());

		if (!userForm.getPassword().equals(userForm.getRepassword()))
			throw new RuntimeException("You must confirm your password");
		AppUser user = userService.findUserByEmail(userForm.getEmail());
		if (user != null)
			throw new RuntimeException("This user already exists");
		AppUser appUser = new AppUser();
		appUser.setEmail(userForm.getEmail());
		appUser.setPassword(userForm.getPassword());
		appUser.getRoles().add(RoleEnum.USER);
		userService.createUser(appUser);
		
		return appUser;
	}

}
