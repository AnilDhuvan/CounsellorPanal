package com.nilu.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nilu.entity.Counsellor;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	
	// select * from counsellor_tbl  where email = email and password = password
	public Counsellor findByEmailAndPassword(String email,String password);
	
	// select * from counsellor_tbl where email = email
	public Counsellor findByEmail(String email);
	
}
