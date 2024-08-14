package org.jsp.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


//@Controller
//public class HomeController {
//	@RequestMapping(value = "/hii")
//	@ResponseBody
//	public String hii() {
//		return "welcome to SPRING BOOT DEMO APPLICATION homecontroller";
//	}
//}

@RestController
public class HomeController {
	@PostMapping(value = "/hi")
	public String hii() {
		return "Hello from spring boot demo application";
	}
	
	@GetMapping(value = "/test")
	public String test() {
		return "Testing rest api";
	}
	
	@GetMapping("/sum")
	public String getsum(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		return n1+"+"+n2+"="+(n1+n2);
	}
	
	@GetMapping(value = "/difference")
	public String getdifference(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		return n1+"-"+n2+"="+(n1-n2);
	}
	
	@GetMapping(value = "/product")
	public String getproduct(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		return n1+"*"+n2+"="+(n1*n2);
	}
	
	@GetMapping(value = "/quotient")
	public String getquotient(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		return n1+"/"+n2+"="+(n1/n2);
	}
	
	@GetMapping(value = "/smallest")
	public String getsmallest(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		return n1+"@"+n2+"= is smallest"+Math.min(n1,n2);
	}
	
	@GetMapping(value = "/largest")
	public String getlargest(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		return n1+"@"+n2+"= is largest "+Math.max(n1,n2);
	}
	
	@GetMapping(value = "/reminder")
	public String getreminder(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		return n1+"%"+n2+"="+(n1%n2);
	}
	
	@GetMapping(value = "/Lcm")
	public String getLcm(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		// maximum number between n1 and n2 is stored in lcm
	   int lcm;
	   lcm = (n1 > n2) ? n1 : n2;
	    // Always true
	    while(true) {
	      if( lcm % n1 == 0 && lcm % n2 == 0 ) {
	        System.out.printf("The LCM of %d and %d is %d.", n1, n2, lcm);
	        break;
	      }
	      ++lcm;
	    }
		
		return "lcm between"+n1+" and"+n2+" is: "+lcm;

	}
	
	@GetMapping(value = "/hcf")
	public String gethcf(@RequestParam(name = "n1")int n1,@RequestParam(name = "n2")int n2) {
		int hcf = 0;
		 for (int i = 1; i <= n1 || i <= n2; i++)
	     {
	    if (n1 % i == 0 && n2 % i == 0)
	       hcf = i;
	     }

	   return "lcm between"+n1+" and"+n2+" is: "+hcf;
	}
	
	
}

