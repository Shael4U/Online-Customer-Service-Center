package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.entities.Call;


@Repository
public interface CustomerCallRepository extends JpaRepository<Call, Integer>{

}
