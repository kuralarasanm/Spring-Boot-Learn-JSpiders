package org.jsp.merchantbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer>{
	
	@Query("select m from Merchant m  where m.phone=?1 and m.password=?2")
	public Optional<Merchant> verifyByPhoneAndPassword(long phone, String password);
	
	@Query("select m from Merchant m where m.name=?1" )
	public List<Merchant> findbyname(String name);
	
	@Query("select m from Merchant m where m.phone=?1" )
	public Optional<Merchant> findbyphone(long phone);
	
	@Query("select m from Merchant m where m.email=?1" )
	public Optional<Merchant> findbyemail(String email);
	
	@Query("select m from Merchant m where m.gst_number=?1" )
	public Optional<Merchant> findbygst(String gst_number);
	
	@Query("select m from Merchant m where m.email=?1 and m.password=?2" )
	public Optional<Merchant> verify(String email,String password);
	
	@Query("select m from Merchant m where m.id=?1 and m.password=?2" )
	public Optional<Merchant> verify(int id,String password);
	
	@Query("select m from Merchant m where m.gst_number=?1 and m.password=?2" )
	public Optional<Merchant> verifygst(String gst_number,String password);
}
