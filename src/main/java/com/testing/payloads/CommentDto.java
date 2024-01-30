package com.testing.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	
	private long id;
	
	@NotEmpty(message="Atlest 2 Characters Enter in Body on Comment")
	@NotNull
	@Size(min=5,message="Atlest 5 Characters Enter in Body on Comment")
	private String body;
	
	@NotEmpty(message="Atlest 2 Characters Enter in Email on Comment")
	@NotNull
	@Email(message="Atlest 2 Characters Enter in Email on Comment")
	@Size(min=10,message="Atlest 10 Characters Enter in Email on Comment")
	private String email;
	
	@NotEmpty(message="Atlest 2 Characters Enter in Name on Comment")
	@NotNull
	@Size(min=2,message="Atlest 2 Characters Enter in Name on Comment")
	private String name;
}
