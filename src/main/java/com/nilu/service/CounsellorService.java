package com.nilu.service;

import com.nilu.dto.DashboardResponse;
import com.nilu.entity.Counsellor;

public interface CounsellorService {
	
	
	public Counsellor findByEmail(String email);
	
	public boolean register(Counsellor counsellor);
	
	public Counsellor login(String email, String pwd);
	
	public DashboardResponse getDashboardInfo(Integer counsellorId);

}
