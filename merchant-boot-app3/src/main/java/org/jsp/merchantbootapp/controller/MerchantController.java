package org.jsp.merchantbootapp.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/merchants")
public class MerchantController {
	@Autowired
	private MerchantRepository repository;
    
	@PostMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant) {
		ResponseStructure<Merchant> structure=new ResponseStructure();
		structure.setMessage("Merchant saved");
		structure.setData(repository.save(merchant));
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return structure ;
	} 
	
	@PutMapping
	public ResponseEntity<ResponseStructure> updateMerchant(@RequestBody Merchant merchant) {
		Optional<Merchant>recMerchant=repository.findById(merchant.getId());
		ResponseStructure<Merchant> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			Merchant dbMerchant=recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
			structure.setMessage("Merchant updated");
			structure.setData(repository.save(merchant));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ;
		}
		structure.setMessage("Merchant not updated");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ; 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure> findbyid(@PathVariable(name = "id")int id) {
		Optional<Merchant>recMerchant=repository.findById(id);
		ResponseStructure<Merchant> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			structure.setMessage("Merchant updated");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ; 
		}
		structure.setMessage("Merchant not found");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure> deletebyid(@PathVariable(name = "id")int id) {
		Optional<Merchant>recMerchant=repository.findById(id);
		ResponseStructure<String> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			repository.delete(recMerchant.get());
			structure.setMessage("Merchant deleted");
			structure.setData("deleted successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		structure.setMessage("Merchant not deleted");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ; 
	}
	@GetMapping
	public ResponseEntity<ResponseStructure> findall(){
		ResponseStructure<List<Merchant>> structure=new ResponseStructure();
		List<Merchant>recMerchant=repository.findAll();
		
		if(recMerchant.size()>0) {
			structure.setMessage("Merchant find");
			structure.setData(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		structure.setMessage("Merchant Find");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ;
	}
	
	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure> verifyMerchant(@RequestParam long phone,@RequestParam String password) {
		Optional<Merchant>recMerchant=repository.verify(phone,password);
		ResponseStructure<Merchant> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			structure.setMessage("Merchant find");
			structure.setData(recMerchant.get() );
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		structure.setMessage("Merchant not Verified");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ;
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure> findbyname(@PathVariable String name){
		ResponseStructure<List<Merchant>> structure=new ResponseStructure();
		List<Merchant>recMerchant=repository.findbyname(name);
		if(recMerchant.size()>0) {
			structure.setMessage("Merchant find");
			structure.setData(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		structure.setMessage("Merchant not Find");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ;
		
	}
	
	@GetMapping("/find-by-email/{email}")
	public Optional<Merchant> findbyemail(@PathVariable String email){
		return repository.findbyemail(email);
	}
	@GetMapping("/find-by-gst/{gst_number}")
	public Optional<Merchant> findbygst(@PathVariable String gst_number){
		return repository.findbygst(gst_number);
	}
	
	@PostMapping("/findbyemail")
	public Merchant findMerchant(@RequestParam String email,@RequestParam String password) {
		Optional<Merchant>recMerchant=repository.verify(email,password);
		if(recMerchant.isPresent()) {
			return recMerchant.get() ;
		}
		return null;
}

	@PostMapping("/findbyid")
	public Merchant findMerchantid(@RequestParam int id,@RequestParam String password) {
		Optional<Merchant>recMerchant=repository.verify(id,password);
		if(recMerchant.isPresent()) {
			return recMerchant.get() ;
		}
		return null;
}
	
	@PostMapping("/findbygst")
	public Merchant findMerchantgst(@RequestParam String gst_number,@RequestParam String password) {
		Optional<Merchant>recMerchant=repository.verify(gst_number,password);
		if(recMerchant.isPresent()) {
			return recMerchant.get() ;
		}
		return null;
}
}

