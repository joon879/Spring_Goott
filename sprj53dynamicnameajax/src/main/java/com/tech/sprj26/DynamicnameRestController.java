package com.tech.sprj26;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DynamicnameRestController {
	
	
	
	@RequestMapping("/dynamicnamerest")
	public String dynamicnamerest() {
		
		return "restdata";
	}

}
