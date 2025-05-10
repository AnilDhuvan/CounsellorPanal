package com.nilu.entity;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Counsellor_Data")
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Counsellor {

	@Id
	@Column(updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  counsellorId;
	
	@NotNull(message = "name is required")
	@NotBlank(message = "name cannot be blank")
	@Size(min = 3, max = 50, message = "name must be between minimum of 3 characters "
			+ "and maximum of 50 characters")
	@Column(name = "name", nullable = false, length = 50)
    private String name;
	
	@NotBlank
	@Column(name = "phone", nullable = false)
    private String phone;
	
	@Email
	@NotBlank
	@Column(name = "email", nullable = false, unique = true)
    private String email;
	
	@NotBlank
	@Column(name = "password", nullable = false)
    private String password;
	
    @CreatedDate
    private LocalDate createdDate;
    
    @UpdateTimestamp
    private LocalDate updateDate;


}

