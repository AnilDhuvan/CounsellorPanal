package com.nilu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nilu.dto.ViewEnqsFilterRequest;
import com.nilu.entity.Enquiry;
import com.nilu.service.impl.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryCountroller {
	
	private EnquiryServiceImpl  service;
	
	// setter injection
	public EnquiryCountroller(EnquiryServiceImpl service) {
		this.service = service;
	}


	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		Enquiry enqobj = new Enquiry();
		model.addAttribute("enq", enqobj);
		return "enquiryForm";
	}
	
	@GetMapping("/editEnq")
	public String editEnquiry( @RequestParam("enqId")   Integer enqId,Model model) {
		
		Enquiry enquiry = service.getEnquiryById(enqId);
		model.addAttribute("enq", enquiry);
		return "enquiryForm";
	}

	
	@PostMapping("/addEnq")
	public String handelEnquiry( @ModelAttribute("enq") Enquiry enq, Model model , HttpServletRequest req ) throws Exception {
	
		// get existing session obj
		HttpSession session = req.getSession(false);
		 Integer counsellorId =(Integer) session.getAttribute("counsellorId");
		
		boolean isSaved = service.addEnquiry(enq,counsellorId );
		
		if(isSaved) {
			model.addAttribute("smsg"," enquery is add ...");
		}else {
			model.addAttribute("emsg","enquiry not added..please try again ");
		}
		
		return "enquiryForm";
	}
	
	@GetMapping("/regview")
	public String getAllQuery(Model model,HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		Integer attribute = (Integer)  session.getAttribute("counsellorId");
		
		 List<ViewEnqsFilterRequest> allEnquirys = service.getAllEnquirys(attribute);
		
		model.addAttribute("allQuery", allEnquirys);
		
		ViewEnqsFilterRequest filterReq = new ViewEnqsFilterRequest();
		model.addAttribute("viewEnqFilter", filterReq);
		
		return "viewenquiries";	
	}
	
	
	@PostMapping("/filter-enqs")
	public String getFilterQuery(@ModelAttribute("viewEnqFilter") ViewEnqsFilterRequest viewEnqFilter,HttpServletRequest req , Model model) {
		//get Existing obj
		
		
		HttpSession session = req.getSession(false);
		 if (session == null || session.getAttribute("counsellorId") == null) {
		        return "redirect:/login"; // Redirect if session is invalid
		    }
		Integer counsellorId = (Integer)  session.getAttribute("counsellorId");
		
		
//	List<Enquiry> enquiriesWithFilter = 
			 List<ViewEnqsFilterRequest> enqList = service.getEnquiriesWithFilter(viewEnqFilter, counsellorId);
	           
			

         model.addAttribute("allQuery", enqList);

		return "viewenquiries";
	}
	


}
