package com.demo.developer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bugs {
	private Integer issueID;
	private String title;
	private String desCreationDate;
	private String asignDev;
	private String priority;
	private String status;

	public Bugs(Integer issueID, String title, String desCreationDate, String asignDev, String priority,
			String status) {
		super();
		this.issueID = issueID;
		this.title = title;
		this.desCreationDate = desCreationDate;
		this.asignDev = asignDev;
		this.priority = priority;
		this.status = status;
	}

	public Bugs() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIssueID() {
		return issueID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesCreationDate() {
		return desCreationDate;
	}

	public void setDesCreationDate(String desCreationDate) {
		this.desCreationDate = desCreationDate;
	}

	public String getAsignDev() {
		return asignDev;
	}

	public void setAsignDev(String asignDev) {
		this.asignDev = asignDev;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setIssueID(Integer issueID) {
		this.issueID = issueID;
	}

}
