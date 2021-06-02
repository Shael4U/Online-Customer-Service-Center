package com.cg.ocsc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ocsc.entities.Login;

public interface LoginDao extends JpaRepository<Login, Integer>{
	public Login findByUsernameAndPassword(String username, String password);
}
