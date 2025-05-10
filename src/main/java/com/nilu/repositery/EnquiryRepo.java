package com.nilu.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nilu.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {
    
    @Query(value = "SELECT * FROM enquiry_tbl WHERE counsellor_id = :counsellorId", nativeQuery = true)
    public List<Enquiry> getEnquriesByCounsellorId(@Param("counsellorId") Integer counsellorId);
    
    public boolean findByEmail(String email);
}

