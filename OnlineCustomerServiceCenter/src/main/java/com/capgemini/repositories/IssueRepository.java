package com.capgemini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.entities.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer>{

}
