package com.customerconnect.serviceimplementations;

import java.util.List;

import com.customerconnect.dao.OperatorDao;
import com.customerconnect.daoimplementations.OperatorDaoImplement;
import com.customerconnect.entity.Operator;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.services.OperatorService;

public class OperatorServiceImplement implements OperatorService {

	@Override
	public void addOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException {
		OperatorDao operatorDao = new OperatorDaoImplement();
		operatorDao.addOperator(operator);

	}

	@Override
	public void removeOperator(int operatortId) throws CannotCompleteTaskException, CannotConnectException {
		OperatorDao operatorDao = new OperatorDaoImplement();
		operatorDao.removeOperator(operatortId);
	}

	@Override
	public Operator findOperatorById(int operatorId) throws CannotCompleteTaskException, CannotConnectException {
		OperatorDao operatorDao = new OperatorDaoImplement();
		Operator operator = operatorDao.findOperatorById(operatorId);
		return operator;
	}

	@Override
	public void modifyOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException {
		OperatorDao operatorDao = new OperatorDaoImplement();
		operatorDao.modifyOperator(operator);
	}

	@Override
	public List<Operator> viewAllOperators() throws NoRecordFoundException, CannotConnectException {
		OperatorDao operatorDao = new OperatorDaoImplement();
		List<Operator> operatorsList= operatorDao.viewAllOperators();
		return operatorsList;
	}

}