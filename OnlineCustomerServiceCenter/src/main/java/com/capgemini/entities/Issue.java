package com.capgemini.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;


@Entity
public class Issue implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int issueId;
	
	@NotEmpty(message = "issueType is required")
	private String issueType;
	
	@NotEmpty(message = "Description is required")
	private String description;
	
	@NotEmpty(message = "Description is required")
	private String issueStatus;
	
	
	
	public Issue(int issueId, String issueType, String description, String issueStatus) {
		super();
		this.issueId = issueId;
		this.issueType = issueType;
		this.description = description;
		this.issueStatus = issueStatus;
	}

	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}



	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_issue", joinColumns = { @JoinColumn(name = "issueId") }, inverseJoinColumns = { @JoinColumn(name = "customerId") })
	private Set<Customer> cus = new HashSet<>();

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public Set<Customer> getCus() {
		return cus;
	}

	public void setCus(Set<Customer> cus) {
		this.cus = cus;
	}
	
	public void addCustomer(Customer cus) {
		this.getCus().add(cus);
	}

	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", issueType=" + issueType + ", description=" + description
				+ ", issueStatus=" + issueStatus + ", cus=" + cus + "]";
	}
	
	
}
