package com.airtel.assignment.AirtelAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.assignment.AirtelAssignment.ApiEnum.ApiCalls;
import com.airtel.assignment.AirtelAssignment.Data.BaseData;
import com.airtel.assignment.AirtelAssignment.factory.HandlerFactory;

@RestController
public class HomeController {

	@Autowired
	HandlerFactory handlerFactory;

	@RequestMapping(value = "/get/common_words", method = RequestMethod.POST)
	public BaseData getCommonWords(@RequestBody BaseData data) {
		return handlerFactory.getHandler(ApiCalls.common_words).getCommonWords(data);
	}

}
