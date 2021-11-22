package com.egeneration.appprofiles.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexRestCtrl {
	
	@Value("${basic.message}")
	private String message;
	
	@Value("${welcome.message}")
	private String welcome;
	
	@GetMapping("")
	public HashMap<String, String> index() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("message", message);
		hashMap.put("welcome", welcome);
		return hashMap;
	}
}
