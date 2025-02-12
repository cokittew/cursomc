package com.gustavoferreira.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class Validation extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();
	
	public Validation(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getList() {
		return list;
	}

	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}


}
