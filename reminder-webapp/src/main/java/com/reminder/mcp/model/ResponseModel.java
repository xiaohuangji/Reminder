package com.reminder.mcp.model;

import com.reminder.constant.ResultConstant;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {

	private static final long serialVersionUID = 9164640925985064235L;

	private int code;

	private T result;

	public ResponseModel(T result) {
		this.code = ResultConstant.OP_SUCC;
		this.result = result;
	}

	public ResponseModel(int code, T result) {
		this.code = code;
		this.result = result;
	}

    public ResponseModel() {
    }

    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
