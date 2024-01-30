package com.testing.payloads;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private long id;
	
	@NotNull
	@NotEmpty
	@Size(min=2,message="Atlest 2 Characters Enter in Title on Post")
	private String title;
	
	@NotEmpty
	@NotNull
	@Size(min=5,message="Atlest 5 Characters Enter in Description on Post")
	private String description;
	
	@NotEmpty
	@NotNull
	@Size(min=5,message="Atlest 5 Characters Enter in Content on Post")
	private String content;
	
	
}
