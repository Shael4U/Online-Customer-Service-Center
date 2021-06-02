package com.capgemini.entities;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Call implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int callId;
	private Date callDate;
	private double callDuration;
	private String phoneNumber;
	
	public Call() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Call(int callId, Date callDate, double callDuration, String phoneNumber) {
		super();
		this.callId = callId;
		this.callDate = callDate;
		this.callDuration = callDuration;
		this.phoneNumber = phoneNumber;
	}

	public int getCallId() {
		return callId;
	}

	public void setCallId(int callId) {
		this.callId = callId;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public double getCallDuration() {
		return callDuration;
	}

	public void setCallDuration(double callDuration) {
		this.callDuration = callDuration;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Call [callId=" + callId + ", callDate=" + callDate + ", callDuration=" + callDuration + ", phoneNumber="
				+ phoneNumber + "]";
	}

	
	
}
