package com.project.dropeditions.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.dropeditions.entities.AppUser;

@Repository
public interface UserDao extends JpaRepository<AppUser, Long>{

	public Optional<AppUser> findByEmail(String email);
}
