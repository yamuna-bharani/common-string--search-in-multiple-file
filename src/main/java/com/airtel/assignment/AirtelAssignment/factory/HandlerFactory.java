package com.airtel.assignment.AirtelAssignment.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airtel.assignment.AirtelAssignment.ApiEnum.ApiCalls;
import com.airtel.assignment.AirtelAssignment.Handler.BaseHandler;
import com.airtel.assignment.AirtelAssignment.Handler.FileManipulationApiHandler;


@Component
public class HandlerFactory {

	@Autowired
	private FileManipulationApiHandler fileManipulationApiHandler;

	public BaseHandler getHandler(ApiCalls apiCall) {

		if (apiCall.equals(ApiCalls.common_words)) {
			return fileManipulationApiHandler;
		}

		return null;
	}

}
