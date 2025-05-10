package com.nilu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nilu.dto.ViewEnqsFilterRequest;
import com.nilu.entity.Counsellor;
import com.nilu.entity.Enquiry;
import com.nilu.repositery.CounsellorRepo;
import com.nilu.repositery.EnquiryRepo;
import com.nilu.service.EnquiryService;

import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	private  EnquiryRepo  enqRepo;
	@Autowired
	private CounsellorRepo counRepo;
	
	
//	public EnquiryServiceImpl(EnquiryRepo enqRepo, CounsellorRepo counRepo) {
//		this.enqRepo = enqRepo;
//		this.counRepo = counRepo;
//	}


	
	@Override
	public boolean addEnquiry(Enquiry enq, Integer counserllorId)throws Exception {
		Counsellor counsellor = counRepo.findById(counserllorId).orElse(null);
		if(counsellor == null) {
			throw new Exception("No counsellor found");
		}
		
		// associating counsellor to enquiry
		enq.setCou(counsellor);
		
		Enquiry save = enqRepo.save(enq);
		
		if(save.getEnqId() != null) {
			return true;
		}
		return false;
	}
	

	@Override
	public List<ViewEnqsFilterRequest> getAllEnquirys(Integer counsellorId) {
         List<ViewEnqsFilterRequest> dtosList = new ArrayList<>();	
		
		List<Enquiry> enqsList = enqRepo.getEnquriesByCounsellorId(counsellorId);
		
		enqsList.forEach(e -> {
			ViewEnqsFilterRequest dto = new ViewEnqsFilterRequest();
			BeanUtils.copyProperties(e, dto);
			dtosList.add(dto);
		});
		
		return dtosList;
	}
		

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
	 
		return enqRepo.findById(enqId).orElse(null);
	}
	
	@Override
	public List<ViewEnqsFilterRequest> getEnquiriesWithFilter(ViewEnqsFilterRequest filterReq, Integer counsellorId) {
		
		// QBY   implementation (Dynamic Query Preparation)
		
		Enquiry enq = new Enquiry();  // entity
		
		if (filterReq.getClassmode() != null && !filterReq.getClassmode().isBlank()) {
	        enq.setClassmode(filterReq.getClassmode());
	    }
	    if (filterReq.getCourse() != null && !filterReq.getCourse().isBlank()) {
	        enq.setCourse(filterReq.getCourse());
	    }
	    if (filterReq.getStatus() != null && !filterReq.getStatus().isBlank()) {
	        enq.setStatus(filterReq.getStatus());
	    }
		
		 Counsellor c = counRepo.findById(counsellorId).orElseThrow();
		 enq.setCou(c);     // Set Counsellor reference
		
		
		 List<Enquiry> enqsList = enqRepo.findAll(Example.of(enq));
		 
		 
		 List<ViewEnqsFilterRequest> dtosList = new ArrayList<>();
		 
		 enqsList.forEach(e -> {
			 ViewEnqsFilterRequest dto = new ViewEnqsFilterRequest();
				BeanUtils.copyProperties(e, dto);
				dtosList.add(dto);
			});
		 
			return dtosList;
	}
	
	
	
	public ViewEnqsFilterRequest getEnquiry(Integer enqId) {
		Enquiry entity = enqRepo.findById(enqId).orElseThrow();
		ViewEnqsFilterRequest dto = new ViewEnqsFilterRequest();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}


	@Override
	public boolean findByEmail(String email) {
		return enqRepo.findByEmail(email);
		
	}}
