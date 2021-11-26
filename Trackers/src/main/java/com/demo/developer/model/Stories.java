package com.demo.developer.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer issueID;
	private String title;
	private Date creationDate;
	private String assignedDev;
	private String description;
	private int estimatedPoints;
	private String status;

	public Integer getIssueID() {
		return issueID;
	}

	public Stories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stories(Integer issueID, String title, Date creationDate, String assignedDev, String description,
			int estimatedPoints, String status) {
		super();
		this.issueID = issueID;
		this.title = title;
		this.creationDate = creationDate;
		this.assignedDev = assignedDev;
		this.description = description;
		this.estimatedPoints = estimatedPoints;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getAssignedDev() {
		return assignedDev;
	}

	public void setAssignedDev(String assignedDev) {
		this.assignedDev = assignedDev;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEstimatedPoints() {
		return estimatedPoints;
	}

	public void setEstimatedPoints(int estimatedPoints) {
		this.estimatedPoints = estimatedPoints;
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
