package com.customerconnect.dao;

import java.util.List;

import com.customerconnect.entity.Operator;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

public interface OperatorDao {
	void addOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException;

	void removeOperator(int operatortId) throws CannotCompleteTaskException, CannotConnectException;

	Operator findOperatorById(int operatorId) throws CannotCompleteTaskException, CannotConnectException;

	void modifyOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException;

	List<Operator> viewAllOperators() throws NoRecordFoundException, CannotConnectException;;
}
