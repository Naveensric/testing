
package com.testing.entities;
import java.sql.Timestamp;
import java.util.Date;
//,uniqueConstraints = {@UniqueConstraint(columnNames = {"titles"})}
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="posts" )
public class Post {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long 	id;
	
	@Column(name="titles",unique=true,nullable=false)
	private String 	title;
	
	@Column(name="descriptions",nullable=false)
	private String 	description;
	
	@Column(name="contents",nullable=false)
	private String 	content;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Comment> comment = new HashSet<>();
	
}
