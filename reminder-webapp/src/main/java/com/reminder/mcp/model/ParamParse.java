package com.reminder.mcp.model;

import java.util.ArrayList;
import java.util.List;


public class ParamParse {

    private ResponseModel responseModel;

	private List<Object> objs = new ArrayList<Object>();

    public ResponseModel getResponseModel() {
        return responseModel;
    }

    public void setResponseModel(ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    public List<Object> getObjs() {
		return objs;
	}

	public void setObjs(List<Object> objs) {
		this.objs = objs;
	}
}
