package com.nilu.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardResponse {
	
	private Integer totalEnqs;
	private Integer openEnqs;
	private Integer enrolledEnqs;
	private Integer lostEnqs;
	
	

}