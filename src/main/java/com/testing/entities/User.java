/*
package com.testing.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users" )
public class User {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long 	uId;
	private String 	username;
	private String 	password;
	private String 	name;
	private String 	email;
	private int 	age;
	private long 	mobile;
	private String 	city;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable( name = "user_and_role" , joinColumns = { @JoinColumn(name="user_id",referencedColumnName = "uId")}
										,inverseJoinColumns = { @JoinColumn(name= "role_id",referencedColumnName = "rId")})
	private Set<Role> roles =  new HashSet<Role>();
}
*/