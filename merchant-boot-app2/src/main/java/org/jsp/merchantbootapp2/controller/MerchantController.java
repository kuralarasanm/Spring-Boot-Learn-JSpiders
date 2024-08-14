package org.jsp.merchantbootapp2.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp2.dto.Merchant;
import org.jsp.merchantbootapp2.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/merchants")
public class MerchantController {
	@Autowired
	private MerchantRepository repository;

	@PostMapping
	public Merchant saveMerchant(@RequestBody Merchant merchant) {
		return repository.save(merchant);
	}

	@PutMapping
	public Merchant updateMerchant(@RequestBody Merchant merchant) {
		return repository.save(merchant);
	}

	@GetMapping(value = "/{id}")
	public Merchant findbyid(@PathVariable(name = "id") int id) {
		Optional<Merchant> recMerchant = repository.findById(id);
		if (recMerchant.isPresent()) {
			return recMerchant.get();
		}
		return null;
	}

	@DeleteMapping(value = "/{id}")
	public String deletebyid(@PathVariable(name = "id") int id) {
		Optional<Merchant> recMerchant = repository.findById(id);
		if (recMerchant.isPresent()) {
			repository.delete(recMerchant.get());
			return "merchant is deleted";
		}
		return "merchant not deleted as id is invalid";
	}

	@GetMapping
	public List<Merchant> findall() {
		return repository.findAll();
	}

	@PostMapping("/verify-by-phone")
	public Merchant verifyMerchant(@RequestParam long phone, @RequestParam String password) {
		Optional<Merchant> recMerchant = repository.verify(phone, password);
		if (recMerchant.isPresent()) {
			return recMerchant.get();
		}
		return null;
	}

	@GetMapping("/find-by-name/{name}")
	public List<Merchant> findbyname(@PathVariable String name) {
		return repository.findbyname(name);
	}

	@GetMapping("/find-by-phone/{phone}")
	public Optional<Merchant> findbyphonr(@PathVariable long phone) {
		return repository.findbyphone(phone);
	}

	@GetMapping("/find-by-email/{email}")
	public Optional<Merchant> findbyemail(@PathVariable String email) {
		return repository.findbyemail(email);
	}

	@GetMapping("/find-by-gst/{gst_number}")
	public Optional<Merchant> findbygst(@PathVariable String gst_number) {
		return repository.findbygst(gst_number);
	}

	@PostMapping("/findbyemail")
	public Merchant findMerchant(@RequestParam String email, @RequestParam String password) {
		Optional<Merchant> recMerchant = repository.verify(email, password);
		if (recMerchant.isPresent()) {
			return recMerchant.get();
		}
		return null;
	}

	@PostMapping("/findbyid")
	public Merchant findMerchantid(@RequestParam int id, @RequestParam String password) {
		Optional<Merchant> recMerchant = repository.verify(id, password);
		if (recMerchant.isPresent()) {
			return recMerchant.get();
		}
		return null;
	}

	@PostMapping("/findbygst")
	public Merchant findMerchantgst(@RequestParam String gst_number, @RequestParam String password) {
		Optional<Merchant> recMerchant = repository.verify(gst_number, password);
		if (recMerchant.isPresent()) {
			return recMerchant.get();
		}
		return null;
	}
}
