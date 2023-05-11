package com.customerconnect.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issueId;
    private String issueType;
    private String issueDescription;
    private String issueStatus;
    private LocalDateTime issueDateTime;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "operatorId")
    private Operator operator;

    @OneToOne(mappedBy = "issue", cascade = CascadeType.ALL)
    private Solution solution;

	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Issue(String issueType, String issueDescription, String issueStatus, LocalDateTime issueDateTime,
			Customer customer, Operator operator, Solution solution) {
		super();
		this.issueType = issueType;
		this.issueDescription = issueDescription;
		this.issueStatus = issueStatus;
		this.issueDateTime = issueDateTime;
		this.customer = customer;
		this.operator = operator;
		this.solution = solution;
	}

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

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public LocalDateTime getIssueDateTime() {
		return issueDateTime;
	}

	public void setIssueDateTime(LocalDateTime issueDateTime) {
		this.issueDateTime = issueDateTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, issueDateTime, issueDescription, issueId, issueStatus, issueType, operator,
				solution);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(issueDateTime, other.issueDateTime)
				&& Objects.equals(issueDescription, other.issueDescription) && issueId == other.issueId
				&& Objects.equals(issueStatus, other.issueStatus) && Objects.equals(issueType, other.issueType)
				&& Objects.equals(operator, other.operator) && Objects.equals(solution, other.solution);
	}

	
}