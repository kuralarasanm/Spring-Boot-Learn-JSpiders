package org.jsp.SpringBootpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping(value = "/evenorodd/{num}")
	public String evenorodd(@PathVariable(name = "num") int number) {
		if(number%2==0) {
			return number+" is an even number";
		}else {
			return number+" is an odd number";
		}
	}
}
