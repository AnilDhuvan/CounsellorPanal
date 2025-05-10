package com.nilu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nilu.dto.DashboardResponse;
import com.nilu.entity.Counsellor;
import com.nilu.entity.Enquiry;
import com.nilu.repositery.CounsellorRepo;
import com.nilu.repositery.EnquiryRepo;
import com.nilu.service.CounsellorService;

@Service
public class CounsellorServiceImp implements CounsellorService{
	
//	@Autowired
//	private CounsellorRepo repo;
//	
//	@Autowired
//	private EnquiryRepo  enqRepo;
	
	private CounsellorRepo repo;
	
	private EnquiryRepo  enqRepo;
	
	
	public CounsellorServiceImp(CounsellorRepo repo, EnquiryRepo enqRepo) {
		this.repo = repo;
		this.enqRepo = enqRepo;
	}
	
	
	public Counsellor findByEmail(String email) {	
		return  repo.findByEmail(email);
	}

	
	public boolean register(Counsellor counsellor) {
		Counsellor save = repo.save(counsellor);
		if (save.getCounsellorId() != null) {
			return true;
		} else {
			return false;
		}}

	
	public Counsellor login(String email, String pwd) {
		 
		return repo.findByEmailAndPassword(email, pwd);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
		
		DashboardResponse response = new DashboardResponse();
		
		List<Enquiry> enqList = enqRepo.getEnquriesByCounsellorId(counsellorId);
		
		int totalEnq = enqList.size();
		
		int enrolledEnqs =enqList.stream()
				                                     .filter(e -> e.getStatus() .equals("Enrolled"))
		                                              .collect(Collectors.toList())
		                                               .size();
		
		int lostEnqs =enqList.stream()
                                                       .filter(e -> e.getStatus() .equals("Lost"))
                                                        .collect(Collectors.toList())
                                                           .size();
		
		int openEnqs =enqList.stream()
                                                   .filter(e -> e.getStatus() .equals("Open"))
                                                     .collect(Collectors.toList())
                                                        .size();
		
		
		response.setTotalEnqs(totalEnq);
		response.setLostEnqs(lostEnqs); 
		response.setOpenEnqs(openEnqs);
		response.setEnrolledEnqs(enrolledEnqs);
		
		return response;
	}

}
