package com.customerconnect.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int solutionId;
    private String solutionDescription;
    private LocalDateTime solutionDateTime;

    @OneToOne
    @JoinColumn(name = "issueId")
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "operatorId")
    private Operator operator;

	public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Solution(String solutionDescription, LocalDateTime solutionDateTime, Issue issue, Operator operator) {
		super();
		this.solutionDescription = solutionDescription;
		this.solutionDateTime = solutionDateTime;
		this.issue = issue;
		this.operator = operator;
	}

	public int getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}

	public String getSolutionDescription() {
		return solutionDescription;
	}

	public void setSolutionDescription(String solutionDescription) {
		this.solutionDescription = solutionDescription;
	}

	public LocalDateTime getSolutionDateTime() {
		return solutionDateTime;
	}

	public void setSolutionDateTime(LocalDateTime solutionDateTime) {
		this.solutionDateTime = solutionDateTime;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(issue, operator, solutionDateTime, solutionDescription, solutionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution other = (Solution) obj;
		return Objects.equals(issue, other.issue) && Objects.equals(operator, other.operator)
				&& Objects.equals(solutionDateTime, other.solutionDateTime)
				&& Objects.equals(solutionDescription, other.solutionDescription) && solutionId == other.solutionId;
	}

    
}