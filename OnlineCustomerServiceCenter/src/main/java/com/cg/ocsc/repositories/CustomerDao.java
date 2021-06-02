package com.cg.ocsc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ocsc.entities.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	public List<Customer> findByFullName (String fullName);
	public Optional<Customer> findByEmail(String email);

}
