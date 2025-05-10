package com.nilu.service;

import java.util.List;

import com.nilu.dto.ViewEnqsFilterRequest;
import com.nilu.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnquiry(Enquiry enq,Integer counserllorId) throws Exception;
	
	public List<ViewEnqsFilterRequest> getAllEnquirys(Integer  counsellorId);
	
	public List<ViewEnqsFilterRequest> getEnquiriesWithFilter(ViewEnqsFilterRequest  filterReq , Integer counsellorId);
	
	public Enquiry  getEnquiryById(Integer enqId);
	
	public boolean findByEmail(String email);

}

