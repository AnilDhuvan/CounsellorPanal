package com.nilu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewEnqsFilterRequest {

	private Integer enqId;
	private String stuName;
	private String stuPhone;
	private String course;
	private String classmode;
	private String status;
	
	public ViewEnqsFilterRequest( String course, String classmode,
			String status) {
		super();
		this.course = course;
		this.classmode = classmode;
		this.status = status;
	}
	
	
	
}