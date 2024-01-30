package com.testing.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testing.payloads.PostDto;
import com.testing.payloads.PostResponse;
import com.testing.services.PostService;
import com.testing.utils.AppConstants;


@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService post_serv;

	public PostController(PostService post_serv) {
		super();
		this.post_serv = post_serv;
	}

	
	@GetMapping("/url")
	public String page() {
		
		return "enter";
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> getAllPostDetails(){
		List<PostDto> findAllPostDetails = post_serv.findAllPostDetails();
		return new ResponseEntity<>(findAllPostDetails,HttpStatus.FOUND);
	}
	
	@GetMapping("/s/{id}")
	public ResponseEntity<PostDto> getOneDetailsByID(@PathVariable Long id){
		PostDto getOneDetailByID = post_serv.findOneDetailsById(id);
		return ResponseEntity.ok(getOneDetailByID);
	}
	
	
	
	@PreAuthorize(value = "hasRole=(ADMIN)")
	@PostMapping
	public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto
												,BindingResult bindingResult	){
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(post_serv.createPost(postDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateOneDetailsByID(@Valid @RequestBody PostDto postDto
														,BindingResult bindingResult
														,@PathVariable Long id){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
		}
		PostDto getOneUpdatedDetailByID = post_serv.findAndUpdateOneDetailsById(postDto,id);
		return new ResponseEntity<>(getOneUpdatedDetailByID,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
			post_serv.findAndDeleteById(id);
		return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
	}
	
	// localhost:8080/Test/api/posts/page1?pageNo=1&pageSize=10
	@GetMapping("/page1")
	public PostResponse getAllPostDetailsInPage(
			@RequestParam (value="pageNo",defaultValue=AppConstants.DEFAULT_PAGE_NUMBER,required=false)int pageNo ,
			@RequestParam (value="pageSize", defaultValue=AppConstants.DEFAULT_PAGE_SIZE, required=false)int pageSize ){
		PostResponse findAllPostDetailsInPage = post_serv.findAllPostDetailsInPage(pageNo,pageSize);
		return findAllPostDetailsInPage;
	}
	
	
	@GetMapping("/pagesort")
	public ResponseEntity<PostResponse> getAllPostDetailsInPageSort(
			@RequestParam (value="pageNo",defaultValue=AppConstants.DEFAULT_PAGE_NUMBER,required=false)int pageNo ,
			@RequestParam (value="pageSize", defaultValue=AppConstants.DEFAULT_PAGE_SIZE, required=false)int pageSize,
			@RequestParam (value="sortField", defaultValue=AppConstants.DEFAULT_SORT_BY,required=false)String sortField,
			@RequestParam (value="direction",defaultValue=AppConstants.DEFAULT_SORT_DIRECTION,required=false)String direction
			){
		PostResponse findAllPostDetailsInPageSort = post_serv.findAllPostDetailsInPageSort(pageNo,pageSize,sortField,direction);
		return new ResponseEntity<>(findAllPostDetailsInPageSort,HttpStatus.FOUND);
	}
	
}
