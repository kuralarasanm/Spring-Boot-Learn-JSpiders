package org.jsp.merchantbootapp.controller;

import java.util.List;
import java.util.Optional;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/merchants")
public class MerchantController {
	@Autowired
	private MerchantRepository repository;

	@PostMapping
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setMessage("Merchant saved");
		structure.setData(repository.save(merchant));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return structure;
	}

	@PutMapping
	public ResponseStructure<Merchant> updateMerchant(@RequestBody Merchant merchant) {
		Optional<Merchant> recMerchant = repository.findById(merchant.getId());
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setPassword(merchant.getPassword());
			structure.setMessage("Merchant updated");
			structure.setData(repository.save(merchant));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return structure;
		}
		structure.setData(null);
		structure.setMessage("Merchant not updated id is invalid");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return structure;
	}

	@GetMapping(value = "/{id}")
	public ResponseStructure<Merchant> findbyid(@PathVariable(name = "id") int id) {
		Optional<Merchant> recMerchant = repository.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant updated");
			structure.setStatuscode(HttpStatus.OK.value());
			return structure;
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

	@PostMapping(value = "/verifyByPhone")
	public Merchant verifyMerchant(@RequestParam long phone, @RequestParam String password) {
		Optional<Merchant> recMerchant = repository.verifyByPhoneAndPassword(phone, password);
		if (recMerchant.isPresent()) {
			return recMerchant.get();
		} else {
			return null;
		}
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
