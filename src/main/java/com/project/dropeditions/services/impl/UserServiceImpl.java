package com.project.dropeditions.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dropeditions.dao.UserDao;
import com.project.dropeditions.entities.AppUser;
import com.project.dropeditions.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AppUser createUser(AppUser user) {
		// TODO Auto-generated method stub
		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return userDao.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		
		userDao.deleteById(id);
		
	}

	@Override
	public AppUser modifyUser(AppUser newUser, Long id) {
		// TODO Auto-generated method stub

		AppUser updatedUser= userDao.findById(id) //
			      .map(user -> {
			    	  user.setPassword(newUser.getPassword());
			        return userDao.save(user);
			      }) //
			      .orElseGet(() -> {
			    	  newUser.setId(id);
			        return userDao.save(newUser);
			      });

		return updatedUser;
	}

	@Override
	public AppUser getById(Long id) {
		// TODO Auto-generated method stub
		Optional<AppUser> userOpt = userDao.findById(id);
        AppUser user = userOpt.orElse(null);
        return user;
	}

	@Override
	public List<AppUser> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public AppUser findUserByEmail(String email) {
		
		Optional<AppUser> userOpt = userDao.findByEmail(email);
        AppUser user = userOpt.orElse(null);
      
        return user;
	}

}
