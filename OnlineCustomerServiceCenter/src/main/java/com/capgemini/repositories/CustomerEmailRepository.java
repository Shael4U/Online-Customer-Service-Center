package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Customer;

@Repository
public interface CustomerEmailRepository extends JpaRepository<Customer, Integer>{

	@Query("SELECT cd from Customer cd where cd.email=?1")
	public Customer findByEmail(String email);
}
