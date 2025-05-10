package com.nilu.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="enquiry_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	
	@NotNull(message = "name is required")
	@NotBlank(message = "name cannot be blank")
	@Size(min = 3, max = 50, message = "name must be between minimum of 3 characters "
			+ "and maximum of 50 characters")
	@Column(name = "stuName", nullable = false, length = 50)
	private String stuName;
	
	@NotBlank
	@Column(name = "stuPhone", nullable = false)
	private String stuPhone;
	@Email
	@NotBlank
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@NotBlank
	@Column(name = "classmode", nullable = false)
	private String classmode;
	@NotBlank
	@Column(name = "course", nullable = false)
	private String course;
	@NotBlank
	@Column(name = "status", nullable = false)
	private String status;
	
	@CreationTimestamp
	private LocalDate  createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@ManyToOne
	@JoinColumn(name="counsellor_id")
	private Counsellor cou;

}

